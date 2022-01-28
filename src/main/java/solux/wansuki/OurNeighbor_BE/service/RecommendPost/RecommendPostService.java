package solux.wansuki.OurNeighbor_BE.service.RecommendPost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.CommentRepository;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPostRepository;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostUpdateDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendPostService {
    private final RecommendPostRepository recommendPostRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, RecommendPostUpdateDto requestDto) {
        RecommendPost RecommendPost = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        RecommendPost.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    public Long save(RecommendPostSaveDto saveDto) {
        return recommendPostRepository.save(saveDto.toEntity()).getId();
    }

    public List<CommentResponseDto> getComments(Long id) {
        List<CommentResponseDto> responseDtos = new ArrayList<>();
        List<Comment> comments = commentRepository.findByRecommendPostId(id);
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getId())
                    .content(comment.getContent())
                    .userNickName(comment.getMember().getNickName())
                    .build();
            responseDtos.add(commentResponseDto);
        }
        return responseDtos;
    }


    public RecommendPostResponseDto findById(Long id) {
            RecommendPost entity = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

            return new RecommendPostResponseDto(entity);
    }
}

