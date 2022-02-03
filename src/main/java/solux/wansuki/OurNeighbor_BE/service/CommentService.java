package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.CommentRepository;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.GatheringRepository;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPostRepository;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoodsRepository;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentSaveDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final GatheringRepository gatheringRepository;
    private final RecommendPostRepository recommendPostRepository;
    private final UsedGoodsRepository usedGoodsRepository;

    @Transactional
    public Long save(CommentSaveDto saveDto, Long postId, User user) {
        Long id = commentRepository.save(saveDto.toEntity()).getId();
        Member member = memberRepository.findByLoginId(user.getUsername())
                .orElseThrow(()->new IllegalArgumentException("해당 유저 없음"));
        member.addComment(commentRepository.findById(id).orElseThrow());
        if (saveDto.getPostCategory().equals("gathering")) {
            Gathering gathering = gatheringRepository.findById(postId).orElseThrow();
            gathering.addComment(commentRepository.findById(id).orElseThrow());
        }
        else if (saveDto.getPostCategory().equals("recommend")) {
            RecommendPost recommendPost = recommendPostRepository.findById(postId).orElseThrow();
            recommendPost.addComment(commentRepository.findById(id).orElseThrow());
        }
        else if (saveDto.getPostCategory().equals("usedGoods")) {
            UsedGoods usedGoods = usedGoodsRepository.findById(postId).orElseThrow();
            usedGoods.addComment(commentRepository.findById(id).orElseThrow());
        }
        return id;
    }

    public List<Comment> findByMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return member.getComments();
    }

    @Transactional
    public void delete(Long id) { commentRepository.deleteById(id);}
}
