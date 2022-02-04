package solux.wansuki.OurNeighbor_BE.domain.Apartment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;
import solux.wansuki.OurNeighbor_BE.domain.RecommendPost.RecommendPost;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;

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


    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Schedules> schedules = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Gathering> gatherings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<RecommendPost> recommendPosts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UsedGoods> usedGoods = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Notices> notices = new ArrayList<>();

    @Builder
    public Apartment (String apartName) {
        this.apartName = apartName;
    }

    public void addSchedules(Schedules schedules) {
        this.schedules.add(schedules);
        if (schedules.getApartment() != this)
            schedules.setApartment(this);
    }

    public void addGatherings(Gathering gathering) {
        this.gatherings.add(gathering);
        if (gathering.getApartment() != this)
            gathering.setApartment(this);
    }

    public void addRecommendPost(RecommendPost recommendPost) {
        this.recommendPosts.add(recommendPost);
        if (recommendPost.getApartment() != this)
            recommendPost.setApartment(this);
    }

    public void addUsedGoods(UsedGoods usedGoods) {
        this.usedGoods.add(usedGoods);
        if (usedGoods.getApartment() != this)
            usedGoods.setApartment(this);
    }

    public void addNotices(Notices notices) {
        this.notices.add(notices);
        if (notices.getApartment() != this)
            notices.setApartment(this);
    }
}
