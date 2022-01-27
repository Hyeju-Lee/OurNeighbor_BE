package solux.wansuki.OurNeighbor_BE.dto.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private String date;
    private String content;

    @Builder
    public ScheduleResponseDto(String date, String content) {
        this.date = date;
        this.content = content;
    }
}
