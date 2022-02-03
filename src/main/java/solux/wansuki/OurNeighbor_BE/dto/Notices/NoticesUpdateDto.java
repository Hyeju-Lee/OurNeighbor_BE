package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class NoticesUpdateDto {

    private String title;
    private String content;

    @Builder
    public NoticesUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
