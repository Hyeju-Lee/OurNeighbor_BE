package solux.wansuki.OurNeighbor_BE.service.Schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.ScheduleRepository;
import solux.wansuki.OurNeighbor_BE.dto.Schedule.ScheduleSaveDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long save(ScheduleSaveDto saveDto) {
        return scheduleRepository.save(saveDto.toEntity()).getId();
    }

    public List<Schedules> findAll() {
        return scheduleRepository.findAll();
    }
}
