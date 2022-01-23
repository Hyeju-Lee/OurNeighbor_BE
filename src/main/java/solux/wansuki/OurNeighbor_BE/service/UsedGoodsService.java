package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoodsRepository;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsedGoodsService {
    private final UsedGoodsRepository usedGoodsRepository;

    @Transactional
    public Long save(UsedGoodsSaveDto saveDto) { return usedGoodsRepository.save(saveDto.toEntity()).getId();}

    @Transactional
    public Long update(Long id, UsedGoodsSaveDto saveDto) {
        UsedGoods usedGoods = usedGoodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 중고거래 게시글이 없습니다"));
        usedGoods.update(saveDto.getTitle(), saveDto.getContent());
        return id;
    }

    public List<UsedGoods> findAll() { return usedGoodsRepository.findAll();}

}
