package solux.wansuki.OurNeighbor_BE.dto.RecommendPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecommendPostSaveDto {
    private String title;
    private String content;
    private String category;

    @Builder
    public RecommendPostSaveDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public RecommendPost toEntity() {
        return RecommendPost.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}