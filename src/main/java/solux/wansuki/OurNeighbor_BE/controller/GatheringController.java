package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

@CrossOrigin
@RequiredArgsConstructor
@RestController

public class GatheringController {
    private final GatheringService gatheringService;

    @PostMapping("/gathering")
    public Long save(
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @RequestParam("title") String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("category") String category,
            @AuthenticationPrincipal User user
            ) throws Exception {
        GatheringSaveDto saveDto = GatheringSaveDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
        return gatheringService.save(saveDto, files, user);
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

    @DeleteMapping("/gathering/{id}")
    public void delete(@PathVariable Long id) {gatheringService.delete(id);}
}
