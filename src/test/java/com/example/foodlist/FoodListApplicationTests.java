package com.example.foodlist;

import com.example.foodlist.dto.SearchLocalReq;
import com.example.foodlist.naverApi.NaverClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class FoodListApplicationTests {
    @Autowired
    private NaverClient client;
    @Test
    void contextLoads() {

        SearchLocalReq request = new SearchLocalReq();
        request.setQuery("갈비집");
        System.out.println(client.localSearch(request));

    }

}
