package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;
import solux.wansuki.OurNeighbor_BE.dto.Schedule.ScheduleResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Schedule.ScheduleSaveDto;
import solux.wansuki.OurNeighbor_BE.service.ScheduleService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public Long save(@RequestBody ScheduleSaveDto saveDto,
                     @AuthenticationPrincipal User user) {
        return scheduleService.save(saveDto, user);
    }

    @GetMapping("/apartment/schedules")
    public List<ScheduleResponseDto> findSchedules(@AuthenticationPrincipal User user) {
        return scheduleService.findSchedules(user);
    }

    @GetMapping("/schedules")
    public List<Schedules> findAll() {
        return scheduleService.findAll();
    }

    @DeleteMapping("/schedules/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }
}
