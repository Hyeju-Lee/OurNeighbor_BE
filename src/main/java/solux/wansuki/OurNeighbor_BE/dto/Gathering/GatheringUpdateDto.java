//수정
package solux.wansuki.OurNeighbor_BE.dto.Gathering;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GatheringUpdateDto {

    private String title;
    private String content;
    private String category;

    @Builder
    public GatheringUpdateDto(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
