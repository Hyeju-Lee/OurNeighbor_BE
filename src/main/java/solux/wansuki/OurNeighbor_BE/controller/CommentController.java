package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentSaveDto;
import solux.wansuki.OurNeighbor_BE.service.CommentService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{postId}")
    public Long saveComment(@RequestBody CommentSaveDto saveDto,
                            @PathVariable Long postId, @AuthenticationPrincipal User user) {
        return commentService.save(saveDto, postId, user);
    }

    @GetMapping("/comment/{memberId}")
    public List<Comment> findCommentByMember(@PathVariable Long memberId) {
        return commentService.findByMember(memberId);
    }
}
