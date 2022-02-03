package solux.wansuki.OurNeighbor_BE.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.Photo.PhotoRepository;
import solux.wansuki.OurNeighbor_BE.dto.Photo.PhotoResponseDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Transactional
    public ResponseEntity<byte[]> getPhoto(Long photoId) throws IOException {
        PhotoResponseDto responseDto = findById(photoId);
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        String path = responseDto.getFilePath();

        InputStream inputStream = new FileInputStream(absolutePath + path);
        byte[] images = IOUtils.toByteArray(inputStream);
        inputStream.close();

        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    public PhotoResponseDto findById(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포토 없음"));

        PhotoResponseDto responseDto = PhotoResponseDto.builder()
                .originalName(photo.getOriginalName())
                .filePath(photo.getFilePath())
                .fileSize(photo.getFileSize())
                .build();
        return responseDto;
    }

    @Transactional
    public void delete(Long id) {photoRepository.deleteById(id);}
}
