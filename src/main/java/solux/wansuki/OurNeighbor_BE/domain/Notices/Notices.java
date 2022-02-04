package solux.wansuki.OurNeighbor_BE.domain.Notices;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
//import solux.wansuki.OurNeighbor_BE.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Notices extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 500, nullable = false)
    private String content;
    private String title;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @Builder
    public Notices (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        if (!apartment.getNotices().contains(this))
            apartment.getNotices().add(this);
    }

}
