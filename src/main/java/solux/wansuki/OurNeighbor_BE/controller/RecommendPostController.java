package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostUpdateDto;
import solux.wansuki.OurNeighbor_BE.service.RecommendPost.RecommendPostService;

@RequiredArgsConstructor
@RestController

public class RecommendPostController {
    private final RecommendPostService postsService;

    @PostMapping("/recommend-post")
    public Long save(@RequestBody RecommendPostSaveDto saveDto) {
        return RecommendPostService.save(saveDto);
    }

    @PutMapping("/recommend-post")
    public Long update(@PathVariable Long id, @RequestBody RecommendPostUpdateDto requestDto) {
        return RecommendPostService.update(id, requestDto);
    }

    @GetMapping("/recommend-post")
    public RecommendPostResponseDto findById (@PathVariable Long id) {
        return RecommendPostService.findById(id);
    }
}
