package solux.wansuki.OurNeighbor_BE.service.Notices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Member.Member;
import solux.wansuki.OurNeighbor_BE.domain.Member.MemberRepository;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;
import solux.wansuki.OurNeighbor_BE.domain.Notices.NoticesRepository;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesUpdateDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticesService {
    private final NoticesRepository noticesRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long update(Long id, NoticesUpdateDto requestDto) {
        Notices notices = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        notices.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public Long save(NoticesSaveDto saveDto, User user) {
        Long id = noticesRepository.save(saveDto.toEntity()).getId();
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        Apartment apartment = member.getApartment();
        apartment.addNotices(noticesRepository.findById(id).orElseThrow());
        return id;
    }


    public NoticesResponseDto findById(Long id) {
        Notices entity = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        NoticesResponseDto responseDto = NoticesResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
        return responseDto;
    }

    public List<NoticesResponseDto> getNotices(User user) {
        Member member = memberRepository.findByLoginId(user.getUsername()).orElseThrow();
        List<Notices> notices = member.getApartment().getNotices();
        List<NoticesResponseDto> responseDtos = new ArrayList<>();
        for (Notices notice : notices) {
            NoticesResponseDto dto = NoticesResponseDto.builder()
                    .id(notice.getId())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .build();
            responseDtos.add(dto);
        }
        return responseDtos;
    }

    @Transactional
    public void deleteById(Long id) {
        noticesRepository.deleteById(id);
    }
}