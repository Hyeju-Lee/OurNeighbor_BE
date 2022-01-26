package solux.wansuki.OurNeighbor_BE.dto.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String userNickName;
    private String content;

    @Builder
    public CommentResponseDto (Long commentId, String userNickName, String content) {
        this.commentId = commentId;
        this.userNickName = userNickName;
        this.content = content;
    }

}
