package solux.wansuki.OurNeighbor_BE.dto.RecommendPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecommendPostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String category;
    private List<Long> photoIds;
    private String author;
    private LocalDateTime createdDate;

    @Builder
    public RecommendPostResponseDto(Long id, String title, String content, String category
            , List<Long> photoIds, String author, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.photoIds = photoIds;
        this.author = author;
        this.createdDate = createdDate;
    }

}