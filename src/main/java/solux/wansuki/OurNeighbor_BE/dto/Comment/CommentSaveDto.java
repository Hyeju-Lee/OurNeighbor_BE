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

    @Builder
    public CommentSaveDto(String content) {
        this.content = content;
        this.postCategory = getPostCategory();
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
