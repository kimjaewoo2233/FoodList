package com.example.foodlist.controller;


import com.example.foodlist.wishlist.dto.WishListEntityDto;
import com.example.foodlist.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor    //lombok을 활용한 주입
public class ApiController {
    //html파일은 template 디렉토리 안에
    //js파일은 static 안에 들어가면 된다
    private final WishListService wishListService;

    @GetMapping("/search")
    public WishListEntityDto search(@RequestParam String query){    //query가 검색어임(갈비집)
            return wishListService.search(query);
    }
    @PostMapping("")
    public WishListEntityDto add(@RequestBody WishListEntityDto wishListEntityDto){
            log.info("Entity===={}",wishListEntityDto);

            return wishListService.add(wishListEntityDto);
    }
    @GetMapping("/all")
    public List<WishListEntityDto> findAll(){
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
                wishListService.delete(index);
    }
    //업데이트를 하는 것이기에 Post방식
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
            wishListService.addVisit(index);
    }
}
