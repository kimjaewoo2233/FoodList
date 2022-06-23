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
}
