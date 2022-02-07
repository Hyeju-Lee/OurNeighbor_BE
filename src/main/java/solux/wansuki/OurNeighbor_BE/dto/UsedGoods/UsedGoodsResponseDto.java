package solux.wansuki.OurNeighbor_BE.dto.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class UsedGoodsResponseDto {
    private Long id;
    private String title;
    private String content;
    private List<Long> photoId;
    private String author;
    private boolean complete;
    private LocalDateTime createdDate;

    @Builder
    public UsedGoodsResponseDto (Long id, String title, String content, List<Long> photoId
            , String author, boolean complete, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.photoId = photoId;
        this.author = author;
        this.complete = complete;
        this.createdDate = createdDate;
    }
}
