package com.example.foodlist.wishlist.service;

import com.example.foodlist.dto.SearchImageReq;
import com.example.foodlist.dto.SearchLocalReq;
import com.example.foodlist.naverApi.NaverClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public void search(String query){
        //지역 검색
            var searchLocalReq = new SearchLocalReq();
            searchLocalReq.setQuery(query);

            var searchLocalRes = naverClient.localSearch(searchLocalReq);
            //결과가 있을수도 있고 없을 수도 있따.
            if(searchLocalRes.getTotal() >0){   //검색된 데이터가 있을때이다. 그래서 이경우는 null이 발생할 수 없다.
                var item = searchLocalRes.getItems().stream().findFirst().get();        //첫번째 아이템을 꺼낸다.

                var imageQuery = item.getTitle().replaceAll("<[^>]*>","");  //괄호랑 이상한 문자들은 다 없에버린다.
                var searchImageReq = new SearchImageReq();
                searchImageReq.setQuery(imageQuery);    //바꾼 값을 넣음 -- 이 결과도 있는지 검사해야한다. NaverClient에 메소드에 Request를 보낼것이다.

                var searchImageRes = naverClient.imageSearch(searchImageReq);
                if(searchImageRes.getTotal() > 0){  //이미지에 요청을 보냈을때 이미지 콘텐츠들이 있을 경우


                }




            }

        //이미지 검색


        //결과

    }
}
