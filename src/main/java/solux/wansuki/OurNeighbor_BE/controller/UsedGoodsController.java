package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solux.wansuki.OurNeighbor_BE.domain.UsedGoods.UsedGoods;
import solux.wansuki.OurNeighbor_BE.dto.Comment.CommentResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsResponseDto;
import solux.wansuki.OurNeighbor_BE.dto.UsedGoods.UsedGoodsSaveDto;
import solux.wansuki.OurNeighbor_BE.service.UsedGoodsService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UsedGoodsController {

    private final UsedGoodsService usedGoodsService;

    //@PostMapping("/usedGoods")
    //public Long save(@RequestBody UsedGoodsSaveDto saveDto) {return usedGoodsService.save(saveDto);}

    @PostMapping("/used-goods")
    public Long save(
            @RequestParam(value = "file", required = false) List<MultipartFile> files,
            @RequestParam("title") String title,
            @RequestParam(value = "content", required = false) String content,
            @AuthenticationPrincipal User user
            ) throws Exception{
        UsedGoodsSaveDto saveDto = UsedGoodsSaveDto.builder()
                .title(title)
                .content(content)
                .build();
        return usedGoodsService.save(saveDto, files, user);
    }

    @PutMapping("/used-goods/{usedGoods_id}")
    public Long update(@PathVariable Long usedGoods_id, @RequestBody UsedGoodsSaveDto saveDto) {
        return usedGoodsService.update(usedGoods_id, saveDto);
    }

    @GetMapping("/used-goods/comments/{id}")
    public List<CommentResponseDto> getComments(@PathVariable Long id) {
        return usedGoodsService.getComments(id);
    }

    @GetMapping("/used-goods/{id}")
    public UsedGoodsResponseDto findById(@PathVariable Long id) {
        return usedGoodsService.findById(id);
    }

    @GetMapping("/used-goods")
    public List<UsedGoods> findAll() {
        return usedGoodsService.findAll();
    }

    @DeleteMapping("/used-goods/{id}")
    public void delete(@PathVariable Long id) {usedGoodsService.delete(id);}
}
