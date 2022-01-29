package solux.wansuki.OurNeighbor_BE.dto.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;

@Getter
@NoArgsConstructor
public class ScheduleSaveDto {
    private String date;
    private String title;
    @Builder
    public ScheduleSaveDto(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public Schedules toEntity() {
        return Schedules.builder()
                .date(date)
                .title(title)
                .build();
    }
}
