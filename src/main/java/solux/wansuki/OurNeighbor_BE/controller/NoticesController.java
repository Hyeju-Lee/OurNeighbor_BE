package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesUpdateDto;
import solux.wansuki.OurNeighbor_BE.service.Notices.NoticesService;


@RequiredArgsConstructor
@RestController

public class NoticesController {
    private final NoticesService noticesService;

    @PostMapping("/Notice")
    public Long save(@RequestBody NoticesSaveDto saveDto) {
        return noticesService.save(saveDto);
    }

    @PutMapping("/Notice")
    public Long update(@PathVariable Long id, @RequestBody NoticesUpdateDto requestDto) {
        return noticesService.update(id, requestDto);
    }

    @GetMapping("/Notice/{id}")
    public NoticesResponseDto findById (@PathVariable Long id) {
        return noticesService.findById(id);
    }
}
