package solux.wansuki.OurNeighbor_BE.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LatestPostResponseDto {
    private Long id;
    private String postType;
    private String title;
    private LocalDateTime createdDate;

    @Builder
    public LatestPostResponseDto(Long id, String postType, String title, LocalDateTime createdDate) {
        this.id = id;
        this.postType = postType;
        this.title = title;
        this.createdDate = createdDate;
    }

}
