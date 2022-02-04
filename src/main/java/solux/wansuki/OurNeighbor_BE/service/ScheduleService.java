package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Comment.Comment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.Schedules;
import solux.wansuki.OurNeighbor_BE.domain.Schedule.ScheduleRepository;
import solux.wansuki.OurNeighbor_BE.dto.Schedule.ScheduleResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Schedule.ScheduleSaveDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(ScheduleSaveDto saveDto, User user) {
        Long id = scheduleRepository.save(saveDto.toEntity()).getId();
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        Apartment apartment = member.getApartment();
        apartment.addSchedules(scheduleRepository.findById(id).orElseThrow());
        return id;
    }

    @Transactional
    public List<ScheduleResponseDto> findSchedules(User user) {
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<Schedules> schedules = member.getApartment().getSchedules();
        List<ScheduleResponseDto> responseDtos = new ArrayList<>();
        for (Schedules schedule : schedules) {
            ScheduleResponseDto responseDto = ScheduleResponseDto.builder()
                    .id(schedule.getId())
                    .date(schedule.getDate())
                    .title(schedule.getTitle())
                    .build();
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public List<Schedules> findAll() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
