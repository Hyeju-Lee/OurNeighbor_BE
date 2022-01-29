package solux.wansuki.OurNeighbor_BE.dto.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UsedGoodsResponseDto {
    private Long id;
    private String title;
    private String content;
    private List<Long> photoId;

    @Builder
    public UsedGoodsResponseDto (Long id, String title, String content, List<Long> photoId) {
        this.title = title;
        this.content = content;
        this.photoId = photoId;
    }
}
