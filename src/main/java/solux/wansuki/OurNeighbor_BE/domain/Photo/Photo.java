package solux.wansuki.OurNeighbor_BE.domain.Photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usedGoods_id")
    private UsedGoods usedGoods;

    @Builder
    public Photo(String originalName, String filePath, Long fileSize) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setUsedGoods(UsedGoods usedGoods) {
        if (this.usedGoods != null) {
            this.usedGoods.getPhotos().remove(this);
        }
        this.usedGoods = usedGoods;
        //무한 루프 빠지지 않도록
        if (!usedGoods.getPhotos().contains(this))
            usedGoods.getPhotos().add(this);
    }
}
