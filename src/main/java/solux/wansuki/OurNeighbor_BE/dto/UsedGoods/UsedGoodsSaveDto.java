package solux.wansuki.OurNeighbor_BE.dto.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;

@Getter
@NoArgsConstructor
public class UsedGoodsSaveDto {
    private String title;
    private String content;
    private boolean complete;

    @Builder
    public UsedGoodsSaveDto(String title, String content, boolean complete) {
        this.title = title;
        this.content = content;
        this.complete = complete;
    }

    public UsedGoods toEntity() {
        return UsedGoods.builder()
                .title(title)
                .content(content)
                .complete(complete)
                .build();
    }
}
