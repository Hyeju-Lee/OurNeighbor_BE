package solux.wansuki.OurNeighbor_BE.service.RecommendPost;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.FileHandler;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.CommentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.Photo.PhotoRepository;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPostRepository;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostUpdateDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendPostService {
    private final RecommendPostRepository recommendPostRepository;
    private final CommentRepository commentRepository;
    private final FileHandler fileHandler;
    private final PhotoRepository photoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long update(Long id, RecommendPostUpdateDto requestDto) {
        RecommendPost RecommendPost = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        RecommendPost.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    @Transactional
    public Long save(RecommendPostSaveDto saveDto, List<MultipartFile> files, User user) throws Exception{
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        RecommendPost recommendPost = saveDto.toEntity();
        List<Photo> photoList = fileHandler.parseFileInfo(files);
        if (!photoList.isEmpty()) {
            for (Photo photo : photoList)
                recommendPost.addPhoto(photoRepository.save(photo));
        }
        Long id = recommendPostRepository.save(recommendPost).getId();
        member.addRecommendPost(recommendPostRepository.findById(id).orElseThrow());
        return id;
    }

    public List<CommentResponseDto> getComments(Long id) {
        List<CommentResponseDto> responseDtos = new ArrayList<>();
        List<Comment> comments = commentRepository.findByRecommendPostId(id);
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


    public RecommendPostResponseDto findById(Long id) {
        RecommendPost recommendPost = recommendPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        List<Photo> photoList = recommendPost.getPhotos();
        List<Long> photoIds = new ArrayList<>();
        for (Photo photo: photoList) {
            photoIds.add(photo.getId());
        }
        RecommendPostResponseDto responseDto = RecommendPostResponseDto.builder()
                .id(recommendPost.getId())
                .title(recommendPost.getTitle())
                .content(recommendPost.getContent())
                .category(recommendPost.getCategory())
                .photoIds(photoIds)
                .author(recommendPost.getMember().getNickName())
                .build();
        return responseDto;
    }
}

