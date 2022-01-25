package solux.wansuki.OurNeighbor_BE.service.Gathering;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.Gathering;
import solux.wansuki.OurNeighbor_BE.domain.Gathering.GatheringRepository;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringSaveDto;
import solux.wansuki.OurNeighbor_BE.dto.Gathering.GatheringUpdateDto;


@RequiredArgsConstructor
@Service
public class GatheringService {
    private final GatheringRepository gatheringRepository;

    @Transactional
    public Long update(Long id, GatheringUpdateDto requestDto) {
        Gathering gathering = gatheringRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        gathering.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());
        return id;
    }

    @Transactional
    public Long save(GatheringSaveDto saveDto) {
        return gatheringRepository.save(saveDto.toEntity()).getId();
    }


    public GatheringResponseDto findById(Long id) {
        Gathering entity = gatheringRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new GatheringResponseDto(entity);
    }
}
