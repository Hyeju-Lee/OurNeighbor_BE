package solux.wansuki.OurNeighbor_BE.service.Notices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Notices.Notices;
import solux.wansuki.OurNeighbor_BE.domain.Notices.NoticesRepository;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Notices.NoticesUpdateDto;

@RequiredArgsConstructor
@Service
public class NoticesService {
    private final NoticesRepository noticesRepository;

    @Transactional
    public Long update(Long id, NoticesUpdateDto requestDto) {
        Notices notices = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        notices.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public Long save(NoticesSaveDto saveDto) {
        return noticesRepository.save(saveDto.toEntity()).getId();
    }


    public NoticesResponseDto findById(Long id) {
        Notices entity = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new NoticesResponseDto(entity);
    }
}