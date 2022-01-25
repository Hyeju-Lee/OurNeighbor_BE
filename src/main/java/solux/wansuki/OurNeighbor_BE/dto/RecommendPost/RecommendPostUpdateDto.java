package solux.wansuki.OurNeighbor_BE.dto.RecommendPost;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class RecommendPostUpdateDto {

    private String title;
    private String content;
    private String category;

    @Builder
    public RecommendPostUpdateDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
