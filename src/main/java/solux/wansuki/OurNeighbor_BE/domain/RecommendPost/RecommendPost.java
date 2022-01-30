package solux.wansuki.OurNeighbor_BE.domain.RecommendPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
//import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class RecommendPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendPost_id")
    private Long id;

    @Column (length = 500, nullable = false)
    private String content;
    private String title;
    private String category;

    @OneToMany(mappedBy = "recommendPost", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(
            mappedBy = "recommendPost",
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
    public RecommendPost (String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if (comment.getRecommendPost() != this)
            comment.setRecommendPost(this);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);

        if (photo.getRecommendPost() != this)
            photo.setRecommendPost(this);
    }

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void setMember(Member member) {
        if (this.member != null)
            this.member.getRecommendPosts().remove(this);
        this.member = member;
        if (!member.getRecommendPosts().contains(this))
            member.getRecommendPosts().add(this);
    }

    public void setApartment(Apartment apartment) {
        if (this.apartment != null)
            this.apartment.getRecommendPosts().remove(this);
        this.apartment = apartment;
        if (!apartment.getRecommendPosts().contains(this))
            apartment.getRecommendPosts().add(this);
    }

}
