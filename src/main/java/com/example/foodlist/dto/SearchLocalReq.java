package com.example.foodlist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
public class SearchLocalReq {
    //요청변수를 변수화시켰음 이 변수들을 기본갑승로 요청을 넣는다
    private String query = "";

    private int display = 1;

    private int start = 1;

    private String sort = "random";

    public MultiValueMap<String,String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String,String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);

        return map;
    }

}
