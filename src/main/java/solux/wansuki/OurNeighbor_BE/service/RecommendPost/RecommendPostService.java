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
    private static RecommendPostRepository RecommendPostRepository;

    @Transactional
    public static Long update(Long id, RecommendPostUpdateDto requestDto) {
        RecommendPost RecommendPost = RecommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        RecommendPost.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    public static Long save(RecommendPostSaveDto saveDto) {
        return RecommendPostRepository.save(saveDto.toEntity()).getId();
    }


    public static RecommendPostResponseDto findById(Long id) {
            RecommendPost entity = RecommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

            return new RecommendPostResponseDto(entity);
    }
}

