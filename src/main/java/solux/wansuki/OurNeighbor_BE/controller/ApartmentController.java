package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.GatheringRepository;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.LatestPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.RecommendPost.RecommendPostResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsResponseDto;
import solux.wansuki.OurNeighbor_BE.service.ApartmentService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final MemberRepository memberRepository;
    private final GatheringRepository repository;

    @DeleteMapping("/apartments/{id}")
    public void delete(@PathVariable Long id) {
        apartmentService.delete(id);
    }

    @GetMapping("/apartments")
    public List<Apartment> findAll() {
        return apartmentService.findAll();
    }

    @GetMapping("/apartments/gatherings")
    public List<GatheringResponseDto> getGatherings(@AuthenticationPrincipal User user) {return apartmentService.getGatherings(user);}

    @GetMapping("/apartments/recommend-posts")
    public List<RecommendPostResponseDto> getRecommendPosts(@AuthenticationPrincipal User user) {
        return apartmentService.getRecommendPosts(user);
    }

    @GetMapping("/apartments/used-goods")
    public List<UsedGoodsResponseDto> getUsedGoods(@AuthenticationPrincipal User user) {
        return apartmentService.getUsedGoods(user);
    }

    @GetMapping("/apartments/latest-post")
    public List<LatestPostResponseDto> getLatestPost(@AuthenticationPrincipal User user) {
        return apartmentService.getLatestPost(user);
    }

}
