package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.LoginDto;
import solux.wansuki.OurNeighbor_BE.dto.Member.MemberSaveDto;
import solux.wansuki.OurNeighbor_BE.service.Gathering.GatheringService;
import solux.wansuki.OurNeighbor_BE.service.Member.MemberService;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class GatheringController {
    private final GatheringService gatheringService;

    @PostMapping("/gathering")
    public Long save(@RequestBody GatheringSaveDto saveDto) {
        return gatheringService.save(saveDto);
    }

    @PutMapping("/gathering")
    public Long update(@PathVariable Long id, @RequestBody GatheringUpdateDto requestDto) {
        return gatheringService.update(id, requestDto);
    }

    @GetMapping("/gathering/comment/{gatheringId}")
    public List<CommentResponseDto> getComment(@PathVariable Long gatheringId) {
        return gatheringService.getComment(gatheringId);
    }

    @GetMapping("/gathering/{id}")
    public GatheringResponseDto findById (@PathVariable Long id) {
        return gatheringService.findById(id);
    }
}
