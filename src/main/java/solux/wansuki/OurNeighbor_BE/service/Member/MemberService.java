package solux.wansuki.OurNeighbor_BE.service.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.Security.Jwt.JwtTokenProvider;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.ApartmentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Token.RefreshToken;
import solux.wansuki.OurNeighbor_BE.domain.Token.RefreshTokenRepository;
import solux.wansuki.OurNeighbor_BE.dto.Member.*;

import java.util.Collection;
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
    private final ApartmentRepository apartmentRepository;

    @Transactional
    public Long signUp(MemberSaveDto memberSaveDto) {
        Apartment apartment;
        if (apartmentRepository.findByApartName(memberSaveDto.getApartName()).isPresent())
            apartment = apartmentRepository.findByApartName(memberSaveDto.getApartName()).orElseThrow();
        else {
            apartment = Apartment.builder()
                    .apartName(memberSaveDto.getApartName()).build();
            apartmentRepository.save(apartment);
        }
        List<String> roles = null;
        if (memberSaveDto.getRoles().equals("user"))
            roles = Collections.singletonList("ROLE_USER");
        else if (memberSaveDto.getRoles().equals("admin"))
            roles = Collections.singletonList("ROLE_ADMIN");

        Member member = Member.builder()
                .name(memberSaveDto.getName())
                .nickName(memberSaveDto.getNickName())
                .email(memberSaveDto.getEmail())
                .password(passwordEncoder.encode(memberSaveDto.getPassword()))
                .loginId(memberSaveDto.getLoginId())
                .roles(roles)
                .apartment(apartment)
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

        member.setRefreshToken(refreshToken);

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

        member.setRefreshToken(token);

        return responseDto;
    }

    public String isLoginIdPresent(String loginId) {
        if (memberRepository.findByLoginId(loginId).isPresent())
            return "present";
        else
            return "not present";
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public MemberResponseDto findById(User user) {
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<String> roles = member.getRoles();
        String role = null;
        if (roles.contains("ROLE_ADMIN"))
            role = "관리자";
        else if (roles.contains("ROLE_USER"))
            role = "주민";
        MemberResponseDto responseDto = MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .role(role)
                .loginId(member.getLoginId())
                .apartName(member.getApartment().getApartName())
                .build();
        return responseDto;
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public String isNickNamePresent(String nickName) {
        if (memberRepository.findByNickName(nickName).isPresent())
            return "present";
        else {
            return "not present";
        }
    }

    @Transactional
    public String update(User user, MemberUpdateDto updateDto) {
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        member.update(updateDto.getNickName(), passwordEncoder.encode(updateDto.getPassword()));
        return "success";
    }
}
