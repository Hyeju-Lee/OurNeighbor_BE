package solux.wansuki.OurNeighbor_BE.domain.UsedGoods;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @OneToMany(mappedBy = "usedGoods")
    private List<Photo> photos = new ArrayList<>();

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
}
