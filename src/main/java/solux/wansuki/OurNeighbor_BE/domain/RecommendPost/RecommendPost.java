package solux.wansuki.OurNeighbor_BE.domain.RecommendPost;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RecommendPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 500, nullable = false)
    private String content;
    private String title;
    private String category;

    @Builder
    public RecommendPost (String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

}