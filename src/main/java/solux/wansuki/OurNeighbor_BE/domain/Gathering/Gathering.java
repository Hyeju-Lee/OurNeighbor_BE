package solux.wansuki.OurNeighbor_BE.domain.Gathering;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
//import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Gathering extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gathering_id")
    private Long id;

    @Column (length = 500, nullable = false)
    private String content;
    private String title;
    private String category;
    private boolean complete;

    @OneToMany(mappedBy = "gathering", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(
            mappedBy = "gathering",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @Builder
    public Gathering (String title, String content, String category, boolean complete) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.complete = complete;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getGathering() != this)
            comment.setGathering(this);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);

        if (photo.getGathering() != this)
            photo.setGathering(this);
    }

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void setMember(Member member) {
        if (this.member != null)
            this.member.getGatherings().remove(this);
        this.member = member;
        if (!member.getGatherings().contains(this))
            member.getGatherings().add(this);
    }

    public void setApartment(Apartment apartment) {
        if (this.apartment != null)
            this.apartment.getGatherings().remove(this);
        this.apartment = apartment;
        if (!apartment.getGatherings().contains(this))
            apartment.getGatherings().add(this);
    }

    public void setComplete() {
        this.complete = true;
    }

}
