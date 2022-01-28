package solux.wansuki.OurNeighbor_BE.domain.Gathering;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
//import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Gathering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gathering_id")
    private Long id;

    @Column (length = 500, nullable = false)
    private String content;
    private String title;
    private String category;

    @OneToMany(mappedBy = "gathering")
    private List<Comment> comments;

    @OneToMany(
            mappedBy = "gathering",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Photo> photos = new ArrayList<>();

    @Builder
    public Gathering (String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
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

}
