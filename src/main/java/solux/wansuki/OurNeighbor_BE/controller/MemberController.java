package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.dto.Member.*;
import solux.wansuki.OurNeighbor_BE.service.Member.MemberService;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/reissue")
    public TokenInfoResponseDto reissue(@RequestBody ReissueRequestDto requestDto) {return memberService.reissue(requestDto);}

    @GetMapping("/member/{loginId}")
    public String isLoginIdPresent(@PathVariable String loginId) {
        return memberService.isLoginIdPresent(loginId);
    }

    @GetMapping("/member")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/member/info")
    public MemberResponseDto findById(@AuthenticationPrincipal User user) {
        return memberService.findById(user);
    }

    @DeleteMapping("/member/{id}")
    public void delete(@PathVariable Long id) {memberService.delete(id);}

    @GetMapping("/member/nick-name/{nickName}")
    public String isNickNamePresent(@PathVariable String nickName) {return memberService.isNickNamePresent(nickName);}

    @PutMapping("/member")
    public String update(@AuthenticationPrincipal User user, @RequestBody MemberUpdateDto updateDto) {
        return memberService.update(user, updateDto);
    }
}
