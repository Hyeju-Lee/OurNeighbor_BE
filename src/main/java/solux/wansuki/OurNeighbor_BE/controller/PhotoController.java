package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import solux.wansuki.OurNeighbor_BE.dto.Photo.PhotoResponseDto;
import solux.wansuki.OurNeighbor_BE.service.PhotoService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping(
            value = "/photo/{photoId}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long photoId) throws IOException {
        return photoService.getPhoto(photoId);
    }
}
