package com.example.foodlist.naverApi;


import com.example.foodlist.dto.SearchImageReq;
import com.example.foodlist.dto.SearchImageRes;
import com.example.foodlist.dto.SearchLocalReq;
import com.example.foodlist.dto.SearchLocalRes;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;

@Service
public class NaverClient {

    @Value("${naver.url.client.id}")
    private String naverClientId;
    //만약 값이 없으면 애플리케이션이 구동되지 않는다
    @Value("${naver.url.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes localSearch(SearchLocalReq request){
        var uri = UriComponentsBuilder
                .fromUriString(naverLocalSearchUrl)
                .queryParams(request.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers =new HttpHeaders();
        headers.set("X-Naver-Client-Id", "13De2BDkkPwBrZddTNyQ");
        headers.set("X-Naver-Client-Secret", "");
        //restTemplate에서는 HttpEntity 타입을 매개변수로 받기에 HttpEntity로 header값들을 넣어야한다
        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};
        //제네릭을 사용한경우 그 타입으로 클래스 타입을 지정할 수 없기에 ParameterizedType을 사용해서 사용해야한다
        ResponseEntity<SearchLocalRes> responseentity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseentity.getBody();
    }


    public SearchLocalRes imageSearch(SearchImageReq request){
        var uri = UriComponentsBuilder
                .fromUriString(naverImageSearchUrl)
                .queryParams(request.toMultiValueMap())
                .build()
                .encode()
                .toUri();
        //UriComponentsBuilder는  여러개의 파라미터를 연결하여 URL형태로 만들어주는 것이다
        var headers =new HttpHeaders();
        headers.set("X-Naver-Client-Id", "13De2BDkkPwBrZddTNyQ");
        headers.set("X-Naver-Client-Secret", "");

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};  //추상클래스임

        var respopnseEntiy = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return respopnseEntiy.getBody();
    }

}
