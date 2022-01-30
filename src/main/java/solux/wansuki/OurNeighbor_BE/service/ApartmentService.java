package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.ApartmentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsResponseDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void delete(Long id) {
        apartmentRepository.deleteById(id);
    }

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public List<GatheringResponseDto> getGatherings(User user){
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<Gathering> gatherings = member.getApartment().getGatherings();
        List<GatheringResponseDto> responseDtos = new ArrayList<>();
        for (Gathering gathering : gatherings) {
            List<Photo> photoList = gathering.getPhotos();
            List<Long> photoIds = new ArrayList<>();
            for (Photo photo: photoList) {
                photoIds.add(photo.getId());
            }
            GatheringResponseDto responseDto = GatheringResponseDto.builder()
                    .id(gathering.getId())
                    .title(gathering.getTitle())
                    .content(gathering.getContent())
                    .category(gathering.getCategory())
                    .photoIds(photoIds)
                    .author(gathering.getMember().getNickName())
                    .build();
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public List<RecommendPostResponseDto> getRecommendPosts(User user){
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<RecommendPost> recommendPosts = member.getApartment().getRecommendPosts();
        List<RecommendPostResponseDto> responseDtos = new ArrayList<>();
        for (RecommendPost recommendPost : recommendPosts) {
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
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public List<UsedGoodsResponseDto> getUsedGoods(User user){
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<UsedGoods> usedGoodsList = member.getApartment().getUsedGoods();
        List<UsedGoodsResponseDto> responseDtos = new ArrayList<>();
        for (UsedGoods usedGoods : usedGoodsList) {
            List<Photo> photoList = usedGoods.getPhotos();
            List<Long> photoIds = new ArrayList<>();
            for (Photo photo: photoList) {
                photoIds.add(photo.getId());
            }
            UsedGoodsResponseDto responseDto = UsedGoodsResponseDto.builder()
                    .id(usedGoods.getId())
                    .title(usedGoods.getTitle())
                    .content(usedGoods.getContent())
                    .author(usedGoods.getMember().getNickName())
                    .photoId(photoIds)
                    .build();
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }


}
