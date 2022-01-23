package solux.wansuki.OurNeighbor_BE.domain.Apartment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apart_id")
    private Long id;

    private String apartName;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment")
    private List<Member> members = new ArrayList<>();

    @Builder
    public Apartment (String apartName) {
        this.apartName = apartName;
    }
}
