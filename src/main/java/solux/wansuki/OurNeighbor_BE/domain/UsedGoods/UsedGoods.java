package solux.wansuki.OurNeighbor_BE.domain.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class UsedGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usedGoods_id")
    private Long id;

    private String title;

    private String content;

    @OneToMany(
            mappedBy = "usedGoods",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "recommendPost")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public UsedGoods(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
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
}
