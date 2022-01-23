package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.LoginDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;
import solux.wansuki.OurNeighbor_BE.service.Gathering.GatheringService;
import solux.wansuki.OurNeighbor_BE.service.Member.MemberService;

@RequiredArgsConstructor
@RestController

public class GatheringController {
    private final GatheringService GatheringService;

    @PostMapping("/gathering")
    public Long save(@RequestBody GatheringSaveDto saveDto) {
        return GatheringService.save(saveDto);
    }

    @PutMapping("/gathering")
    public Long update(@PathVariable Long id, @RequestBody GatheringUpdateDto requestDto) {
        return GatheringService.update(id, requestDto);
    }

    @GetMapping("/gathering")
    public GatheringResponseDto findById (@PathVariable Long id) {
        return GatheringService.findById(id);
    }
}
