package solux.wansuki.OurNeighbor_BE.domain.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    private String content;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @Builder
    public Schedules(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        if (!apartment.getSchedules().contains(this))
            apartment.getSchedules().add(this);
    }
}
