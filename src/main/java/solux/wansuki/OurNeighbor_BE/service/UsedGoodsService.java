package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.FileHandler;
import solux.wansuki.OurNeighbor_BE.domain.Photo.Photo;
import solux.wansuki.OurNeighbor_BE.domain.Photo.PhotoRepository;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoodsRepository;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsedGoodsService {
    private final UsedGoodsRepository usedGoodsRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;

    //@Transactional
    //public Long save(UsedGoodsSaveDto saveDto) { return usedGoodsRepository.save(saveDto.toEntity()).getId();}

    @Transactional
    public Long save(UsedGoodsSaveDto saveDto, List<MultipartFile> files) throws Exception{
        UsedGoods usedGoods = saveDto.toEntity();
        List<Photo> photoList = fileHandler.parseFileInfo(files);
        if (!photoList.isEmpty()) {
            for (Photo photo : photoList)
                usedGoods.addPhoto(photoRepository.save(photo));
        }
        return usedGoodsRepository.save(usedGoods).getId();
    }

    @Transactional
    public Long update(Long id, UsedGoodsSaveDto saveDto) {
        UsedGoods usedGoods = usedGoodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 중고거래 게시글이 없습니다"));
        usedGoods.update(saveDto.getTitle(), saveDto.getContent());
        return id;
    }

    public List<UsedGoods> findAll() { return usedGoodsRepository.findAll();}

}
