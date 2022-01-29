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

    private String title;

    @ManyToOne
    @JoinColumn(name = "apart_id")
    private Apartment apartment;

    @Builder
    public Schedules(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
        if (!apartment.getSchedules().contains(this))
            apartment.getSchedules().add(this);
    }
}
