package solux.wansuki.OurNeighbor_BE;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    public List<Photo> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {
        List<Photo> fileList = new ArrayList<>();
        if (multipartFiles.isEmpty())
            return fileList;

        if (!CollectionUtils.isEmpty(multipartFiles)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = "photo" + File.separator + currentDate;
            File file = new File(path);

            if (!file.exists()) {
                file.mkdirs();
            }

            for (MultipartFile multipartFile : multipartFiles) {
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                if (ObjectUtils.isEmpty(contentType))
                    break;
                else {
                    if (contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if (contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else
                        break;
                }

                String newFileName = System.nanoTime() + originalFileExtension;
                Photo photo = Photo.builder()
                        .originalName(multipartFile.getOriginalFilename())
                        .filePath(path + File.separator + newFileName)
                        .fileSize(multipartFile.getSize())
                        .build();

                fileList.add(photo);

                file = new File(absolutePath + path + File.separator + newFileName);
                multipartFile.transferTo(file);

                file.setWritable(true);
                file.setReadable(true);
            }
        }
        return fileList;
    }
}
