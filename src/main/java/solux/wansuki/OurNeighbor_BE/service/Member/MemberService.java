package solux.wansuki.OurNeighbor_BE.service.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.dto.Member.LoginDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
    public Long login(LoginDto loginDto) {
        if (memberRepository.findByLoginId(loginDto.getLoginId()).orElse(null) == null) {
            return 1l;
        }

        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return memberRepository.findByLoginId(loginDto.getLoginId()).get().getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
