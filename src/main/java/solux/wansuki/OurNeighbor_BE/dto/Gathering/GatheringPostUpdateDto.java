package solux.wansuki.OurNeighbor_BE.dto.Gathering;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class GatheringPostUpdateDto {

    private String title;
    private String content;

    @Builder
    public GatheringPostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
