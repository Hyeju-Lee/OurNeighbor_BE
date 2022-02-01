//조회
package solux.wansuki.OurNeighbor_BE.dto.Gathering;

import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GatheringResponseDto {

        private Long id;
        private String title;
        private String content;
        private String category;
        private List<Long> photoIds;
        private String author;
        private boolean complete;
        private LocalDateTime createdDate;

        @Builder
        public GatheringResponseDto(Long id, String title, String content, String category
                , List<Long> photoIds, String author, boolean complete, LocalDateTime createdDate) {
                this.id = id;
                this.title = title;
                this.content = content;
                this.category = category;
                this.photoIds = photoIds;
                this.author = author;
                this.complete = complete;
                this.createdDate = createdDate;
        }

}
