package solux.wansuki.OurNeighbor_BE.dto.recommendPost;

import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.posts.posts;

@Getter

public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String category;

    public PostsResponseDto(posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = entity.getCategory();
    }
}
