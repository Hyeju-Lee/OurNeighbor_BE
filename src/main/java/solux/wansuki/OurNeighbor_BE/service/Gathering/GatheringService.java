package solux.wansuki.OurNeighbor_BE.service.Gathering;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.FileHandler;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.CommentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.GatheringRepository;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.Photo.PhotoRepository;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final CommentRepository commentRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;

    @Transactional
    public Long update(Long id, GatheringUpdateDto requestDto) {
        Gathering gathering = gatheringRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        gathering.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());
        return id;
    }

    @Transactional
    public Long save(GatheringSaveDto saveDto, List<MultipartFile> files) throws Exception{
        Gathering gathering = saveDto.toEntity();
        List<Photo> photoList = fileHandler.parseFileInfo(files);
        if (!photoList.isEmpty()) {
            for (Photo photo : photoList) {
                gathering.addPhoto(photoRepository.save(photo));
            }
        }
        return gatheringRepository.save(gathering).getId();
    }

    public List<CommentResponseDto> getComment(Long id) {
        List<CommentResponseDto> responseDtos = new ArrayList<>();
        List<Comment> comments = commentRepository.findByGatheringId(id);
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getId())
                    .content(comment.getContent())
                    .userNickName(comment.getMember().getNickName())
                    .build();
            responseDtos.add(commentResponseDto);
        }
        return responseDtos;
    }

    public GatheringResponseDto findById(Long id) {
        Gathering entity = gatheringRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new GatheringResponseDto(entity);
    }
}
