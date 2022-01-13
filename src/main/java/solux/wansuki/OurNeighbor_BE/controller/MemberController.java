package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.dto.Member.LoginDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.TokenInfoResponseDto;
import solux.wansuki.OurNeighbor_BE.service.Member.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public Long signUp(@RequestBody MemberSaveDto memberSaveDto) {
        return memberService.signUp(memberSaveDto);
    }

    @PostMapping("/login")
    public TokenInfoResponseDto login(@RequestBody LoginDto loginDto) {return memberService.login(loginDto);}

    @GetMapping("/member")
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
