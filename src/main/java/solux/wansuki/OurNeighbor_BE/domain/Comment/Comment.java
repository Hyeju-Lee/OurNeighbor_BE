package solux.wansuki.OurNeighbor_BE.domain.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gathering_id")
    private Gathering gathering;

    @Builder
    public Comment (String content) {
        this.content = content;
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
}
