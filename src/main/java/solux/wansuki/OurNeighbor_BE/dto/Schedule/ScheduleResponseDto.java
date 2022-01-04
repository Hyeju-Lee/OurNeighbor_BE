package solux.wansuki.OurNeighbor_BE.dto.Schedule;

import lombok.Getter;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String date;
    private String content;

    public ScheduleResponseDto(Schedules entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.content = entity.getContent();
    }
}
