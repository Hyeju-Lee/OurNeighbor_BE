package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;
import solux.wansuki.OurNeighbor_BE.service.UsedGoodsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UsedGoodsController {

    private final UsedGoodsService usedGoodsService;

    //@PostMapping("/usedGoods")
    //public Long save(@RequestBody UsedGoodsSaveDto saveDto) {return usedGoodsService.save(saveDto);}

    @PostMapping("/usedGoods")
    public Long save(
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @RequestParam("title") String title,
            @RequestParam(value = "content", required = false) String content
    ) throws Exception{
        UsedGoodsSaveDto saveDto = UsedGoodsSaveDto.builder()
                .title(title)
                .content(content)
                .build();
        return usedGoodsService.save(saveDto, files);
    }

    @PutMapping("/usedGoods/{usedGoods_id}")
    public Long update(@PathVariable Long usedGoods_id, @RequestBody UsedGoodsSaveDto saveDto) {
        return usedGoodsService.update(usedGoods_id, saveDto);
    }

    @GetMapping("/usedGoods")
    public List<UsedGoods> findAll() {
        return usedGoodsService.findAll();
    }
}
