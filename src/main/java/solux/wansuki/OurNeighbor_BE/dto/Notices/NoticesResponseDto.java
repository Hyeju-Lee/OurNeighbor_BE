package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Builder;
import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;

@Getter

public class NoticesResponseDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public NoticesResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}