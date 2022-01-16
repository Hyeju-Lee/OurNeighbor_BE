package solux.wansuki.OurNeighbor_BE.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.posts.PostsRepository;
import solux.wansuki.OurNeighbor_BE.dto.recommendPost.PostsResponseDto;
import web.dto.PostsSaveRequestDto;

@RequiredArgsConstructor
@Service

public class PostsService {
    priate final PostsRepository postsRepository;

    @Transactional
    public Long save (PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id ="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);

    }
}
