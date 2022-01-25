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
    private static GatheringRepository GatheringRepository;

    @Transactional
    public static Long update(Long id, GatheringUpdateDto requestDto) {
        Gathering Gathering = null;
        try {
            Gathering = GatheringRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id =" + id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //Gathering.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());
        Gathering.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    public Long save(GatheringSaveDto saveDto) {
        return GatheringRepository.save(saveDto.toEntity()).getId();
    }


    public GatheringResponseDto findById(Long id) {
        Gathering entity = null;
        try {
            entity = GatheringRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id=" + id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new GatheringResponseDto(entity);
    }
}
