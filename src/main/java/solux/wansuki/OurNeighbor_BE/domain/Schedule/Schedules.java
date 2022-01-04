package solux.wansuki.OurNeighbor_BE.domain.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Schedules(String date, String content) {
        this.date = date;
        this.content = content;
    }
}
