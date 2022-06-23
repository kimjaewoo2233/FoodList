package com.example.foodlist.respository;


import com.example.foodlist.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class service {

    @Autowired
    private WishListService wishListService;

    @Test
    public void test(){
            var result = wishListService.search("갈비집");
            System.out.println(result);

        Assertions.assertNotNull(result);
    }
}
