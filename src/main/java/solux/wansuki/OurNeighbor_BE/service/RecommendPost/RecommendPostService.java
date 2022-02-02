package solux.wansuki.OurNeighbor_BE.service.RecommendPost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPostRepository;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostUpdateDto;

@RequiredArgsConstructor
@Service
public class RecommendPostService {
    private final RecommendPostRepository recommendPostRepository;

    @Transactional
    public Long update(Long id, RecommendPostUpdateDto requestDto) {
        RecommendPost RecommendPost = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        RecommendPost.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    public Long save(RecommendPostSaveDto saveDto) {
        return recommendPostRepository.save(saveDto.toEntity()).getId();
    }


    public RecommendPostResponseDto findById(Long id) {
        RecommendPost entity = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new RecommendPostResponseDto(entity);
    }
}

