package solux.wansuki.OurNeighbor_BE.domain.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class UsedGoods extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usedGoods_id")
    private Long id;

    private String title;

    private String content;

    private boolean complete;

    @OneToMany(
            mappedBy = "usedGoods",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "usedGoods", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @Builder
    public UsedGoods(String title, String content, boolean complete) {
        this.title = title;
        this.content = content;
        this.complete = complete;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setComplete() {
        this.complete = true;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);

        if (photo.getUsedGoods() != this)
            photo.setUsedGoods(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getUsedGoods() != this)
            comment.setUsedGoods(this);
    }

    public void setMember(Member member) {
        if (this.member != null)
            this.member.getUsedGoods().remove(this);
        this.member = member;
        if (!member.getUsedGoods().contains(this))
            member.getUsedGoods().add(this);
    }

    public void setApartment(Apartment apartment) {
        if (this.apartment != null)
            this.apartment.getUsedGoods().remove(this);
        this.apartment = apartment;
        if (!apartment.getUsedGoods().contains(this))
            apartment.getUsedGoods().add(this);
    }
}
