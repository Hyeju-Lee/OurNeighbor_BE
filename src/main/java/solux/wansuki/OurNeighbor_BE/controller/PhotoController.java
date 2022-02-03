package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/photo/{photoId}")
    public void delete(@PathVariable Long photoId) {photoService.delete(photoId);}
}
