package solux.wansuki.OurNeighbor_BE.domain.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private Long responseTo;

    private String commentType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gathering_id")
    private Gathering gathering;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recommendPost_id")
    private RecommendPost recommendPost;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usedGoods_id")
    private UsedGoods usedGoods;

    @Builder
    public Comment (String content, Long responseTo, String commentType) {
        this.content = content;
        this.responseTo = responseTo;
        this.commentType = commentType;
    }

    public void setMember(Member member) {
        this.member = member;
        if (!member.getComments().contains(this))
            member.getComments().add(this);
    }

    public void setGathering(Gathering gathering) {
        this.gathering = gathering;
        if (!gathering.getComments().contains(this))
            gathering.getComments().add(this);
    }

    public void setRecommendPost(RecommendPost recommendPost) {
        this.recommendPost = recommendPost;
        if (!recommendPost.getComments().contains(this))
            recommendPost.getComments().add(this);
    }

    public void setUsedGoods(UsedGoods usedGoods) {
        this.usedGoods = usedGoods;
        if (!usedGoods.getComments().contains(this)) {
            usedGoods.getComments().add(this);
        }
    }
}
