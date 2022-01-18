package solux.wansuki.OurNeighbor_BE.service.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.Security.Jwt.JwtTokenProvider;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Token.RefreshToken;
import solux.wansuki.OurNeighbor_BE.domain.Token.RefreshTokenRepository;
import solux.wansuki.OurNeighbor_BE.dto.Member.LoginDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.ReissueRequestDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.TokenInfoResponseDto;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public Long signUp(MemberSaveDto memberSaveDto) {
        Member member = Member.builder()
                .name(memberSaveDto.getName())
                .nickName(memberSaveDto.getNickName())
                .email(memberSaveDto.getEmail())
                .password(passwordEncoder.encode(memberSaveDto.getPassword()))
                .loginId(memberSaveDto.getLoginId())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return memberRepository.save(member).getId();
    }

    @Transactional
    public TokenInfoResponseDto login(LoginDto loginDto) {
        Member member = memberRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfoResponseDto tokenInfo = jwtTokenProvider.generateToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshTokenInfo(tokenInfo.getRefreshToken()).build();
        refreshTokenRepository.save(refreshToken);

        member.update(refreshToken);

        return tokenInfo;
    }

    @Transactional
    public TokenInfoResponseDto reissue(ReissueRequestDto requestDto) {
        if (!jwtTokenProvider.validateToken(requestDto.getRefreshToken()))
            throw new IllegalArgumentException("refreshToken 검증 실패");

        String accessToken = requestDto.getAccessToken();
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        Member member = memberRepository.findByLoginId(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("해당 유저가 없습니다"));
        String refreshToken = member.getRefreshToken().getRefreshTokenInfo();

        if (!refreshToken.equals(requestDto.getRefreshToken())) {
            throw new IllegalArgumentException("잘못된 refreshToken");
        }

        TokenInfoResponseDto responseDto = jwtTokenProvider.generateToken(authentication);
        RefreshToken token = RefreshToken.builder()
                .refreshTokenInfo(responseDto.getRefreshToken()).build();
        refreshTokenRepository.save(token);

        member.update(token);

        return responseDto;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
