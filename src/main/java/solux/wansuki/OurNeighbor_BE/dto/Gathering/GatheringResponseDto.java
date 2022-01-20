// 모임 모집
package solux.wansuki.OurNeighbor_BE.dto.Gathering;
import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.posts.posts;

@Getter

public class GatheringResponseDto {

    private Long id;
    private String title;
    private String content;
    private String category;

    public GatheringResponseDto(posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = entity.getCategory();
    }

}
