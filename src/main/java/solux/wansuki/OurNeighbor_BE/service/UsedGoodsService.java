package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.FileHandler;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.CommentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.Photo.PhotoRepository;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoodsRepository;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsedGoodsService {
    private final UsedGoodsRepository usedGoodsRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    //@Transactional
    //public Long save(UsedGoodsSaveDto saveDto) { return usedGoodsRepository.save(saveDto.toEntity()).getId();}

    @Transactional
    public Long save(UsedGoodsSaveDto saveDto, List<MultipartFile> files, User user) throws Exception{
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        UsedGoods usedGoods = saveDto.toEntity();
        List<Photo> photoList = fileHandler.parseFileInfo(files);
        if (!photoList.isEmpty()) {
            for (Photo photo : photoList)
                usedGoods.addPhoto(photoRepository.save(photo));
        }
        Long id = usedGoodsRepository.save(usedGoods).getId();
        member.addUsedGoods(usedGoodsRepository.findById(id).orElseThrow());
        Apartment apartment = member.getApartment();
        apartment.addUsedGoods(usedGoodsRepository.findById(id).orElseThrow());
        return id;
    }

    @Transactional
    public Long update(Long id, UsedGoodsSaveDto saveDto) {
        UsedGoods usedGoods = usedGoodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 중고거래 게시글이 없습니다"));
        usedGoods.update(saveDto.getTitle(), saveDto.getContent());
        return id;
    }

    public List<CommentResponseDto> getComments(Long id) {
        List<CommentResponseDto> responseDtos = new ArrayList<>();
        List<Comment> comments = commentRepository.findByUsedGoodsId(id);
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getId())
                    .content(comment.getContent())
                    .userNickName(comment.getMember().getNickName())
                    .responseTo(comment.getResponseTo())
                    .build();
            responseDtos.add(commentResponseDto);
        }
        return responseDtos;
    }

    public UsedGoodsResponseDto findById(Long id) {
        UsedGoods usedGoods = usedGoodsRepository.findById(id).orElseThrow();
        List<Photo> photoList = usedGoods.getPhotos();
        List<Long> photoIds = new ArrayList<>();
        for (Photo photo: photoList) {
            photoIds.add(photo.getId());
        }
        UsedGoodsResponseDto responseDto = UsedGoodsResponseDto.builder()
                .id(usedGoods.getId())
                .title(usedGoods.getTitle())
                .content(usedGoods.getContent())
                .photoId(photoIds)
                .author(usedGoods.getMember().getNickName())
                .createdDate(usedGoods.getCreatedDate())
                .build();
        return responseDto;
    }

    public List<UsedGoods> findAll() { return usedGoodsRepository.findAll();}

    @Transactional
    public void delete(Long id) {usedGoodsRepository.deleteById(id);}

}
