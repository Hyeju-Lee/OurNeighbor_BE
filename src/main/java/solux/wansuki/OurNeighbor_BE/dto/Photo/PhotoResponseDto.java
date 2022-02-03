package solux.wansuki.OurNeighbor_BE.dto.Photo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoResponseDto {
    private String originalName;
    private String filePath;
    private Long fileSize;

    @Builder
    public PhotoResponseDto(String originalName, String filePath, Long fileSize) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
