package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;

@Getter
@NoArgsConstructor

public class NoticesSaveDto {
    private String title;
    private String content;

    @Builder
    public NoticesSaveDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Notices toEntity() {
        return Notices.builder()
                .title(title)
                .content(content)
                .build();
    }
}