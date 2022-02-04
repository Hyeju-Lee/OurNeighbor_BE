package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesUpdateDto;
import solux.wansuki.OurNeighbor_BE.service.Notices.NoticesService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class NoticesController {
    private final NoticesService noticesService;

    @PostMapping("/notices")
    public Long save(@RequestBody NoticesSaveDto saveDto, @AuthenticationPrincipal User user) {
        return noticesService.save(saveDto, user);
    }

    @PutMapping("/notices/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticesUpdateDto requestDto) {
        return noticesService.update(id, requestDto);
    }

    @GetMapping("/notices/{id}")
    public NoticesResponseDto findById (@PathVariable Long id) {
        return noticesService.findById(id);
    }

    @GetMapping("/apartments/notices")
    public List<NoticesResponseDto> getNotices(@AuthenticationPrincipal User user) {
        return noticesService.getNotices(user);
    }

    @DeleteMapping("/notices/{id}")
    public void delete(@PathVariable Long id) {
        noticesService.deleteById(id);
    }
}
