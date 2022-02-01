package solux.wansuki.OurNeighbor_BE.dto.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;

@Getter
@NoArgsConstructor
public class CommentSaveDto {

    private String content;

    private String postCategory;

    private Long responseTo;

    @Builder
    public CommentSaveDto(String content, String postCategory, Long responseTo) {
        this.content = content;
        this.postCategory = postCategory;
        this.responseTo = responseTo;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .responseTo(responseTo)
                .build();
    }
}
