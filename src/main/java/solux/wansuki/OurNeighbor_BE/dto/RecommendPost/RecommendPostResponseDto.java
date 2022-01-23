package solux.wansuki.OurNeighbor_BE.dto.RecommendPost;

import lombok.Getter;

import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;

@Getter

public class RecommendPostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String category;

    public RecommendPostResponseDto(RecommendPost entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = entity.getCategory();
    }

}