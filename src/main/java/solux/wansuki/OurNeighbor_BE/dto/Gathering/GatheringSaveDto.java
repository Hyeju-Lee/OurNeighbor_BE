//등록
package solux.wansuki.OurNeighbor_BE.dto.Gathering;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;

import java.util.List;

@Getter
@NoArgsConstructor
public class GatheringSaveDto {
    private String title;
    private String content;
    private String category;
    private boolean complete;

    @Builder
    public GatheringSaveDto(String title, String content, String category, boolean complete) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.complete = complete;
    }

    public Gathering toEntity() {
        return Gathering.builder()
                .title(title)
                .content(content)
                .category(category)
                .complete(complete)
                .build();
    }
}