package solux.wansuki.OurNeighbor_BE.dto.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private String date;
    private String title;

    @Builder
    public ScheduleResponseDto(String date, String title) {
        this.date = date;
        this.title = title;
    }
}
