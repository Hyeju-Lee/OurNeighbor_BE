package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;

@Getter

public class NoticesResponseDto {

    private Long id;
    private String title;
    private String content;

    public NoticesResponseDto(Notices entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}