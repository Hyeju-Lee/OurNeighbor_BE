package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(MemberSaveDto memberSaveDto) {
        Member member = Member.builder()
                .name(memberSaveDto.getName())
                .nickName(memberSaveDto.getNickName())
                .email(memberSaveDto.getEmail())
                .password(memberSaveDto.getPassword())
                .loginId(memberSaveDto.getLoginId())
                //.roles(Collections.singletonList("ROLE_USER"))
                .build();
        return memberRepository.save(member).getId();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
