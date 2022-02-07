package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Builder;
import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;

import java.time.LocalDateTime;

@Getter

public class NoticesResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public NoticesResponseDto(Long id, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

}