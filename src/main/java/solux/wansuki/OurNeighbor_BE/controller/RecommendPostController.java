package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostUpdateDto;
import solux.wansuki.OurNeighbor_BE.service.RecommendPost.RecommendPostService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController

public class RecommendPostController {
    private final RecommendPostService recommendPostService;

    @PostMapping("/recommend-posts")
    public Long save(
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @RequestParam("title") String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("category") String category
    ) throws Exception {
        RecommendPostSaveDto saveDto = RecommendPostSaveDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
        return recommendPostService.save(saveDto, files);
    }

    @PutMapping("/recommend-posts")
    public Long update(@PathVariable Long id, @RequestBody RecommendPostUpdateDto requestDto) {
        return recommendPostService.update(id, requestDto);
    }

    @GetMapping("/recommend-posts/comments/{id}")
    public List<CommentResponseDto> getComments(@PathVariable Long id) {
        return recommendPostService.getComments(id);
    }

    @GetMapping("/recommend-posts/{id}")
    public RecommendPostResponseDto findById (@PathVariable Long id) {
        return recommendPostService.findById(id);
    }
}
