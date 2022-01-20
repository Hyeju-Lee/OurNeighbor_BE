// 공지사항

package solux.wansuki.OurNeighbor_BE.dto.Notices;

import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.posts.posts;

@Getter

public class NoticeResponseDto {

    //private Long id;
    private String title;
    private String content;

    public NoticeResponseDto(posts entity) {
        //this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}