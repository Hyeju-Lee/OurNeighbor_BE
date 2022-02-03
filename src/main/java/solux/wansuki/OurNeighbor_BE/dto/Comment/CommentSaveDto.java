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

    private String commentType;

    @Builder
    public CommentSaveDto(String content, String postCategory, Long responseTo, String commentType) {
        this.content = content;
        this.postCategory = postCategory;
        this.responseTo = responseTo;
        this.commentType = commentType;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .responseTo(responseTo)
                .commentType(commentType)
                .build();
    }
}
