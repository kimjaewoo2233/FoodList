package com.example.foodlist.wishlist.service;

import com.example.foodlist.dto.SearchImageReq;
import com.example.foodlist.dto.SearchLocalReq;
import com.example.foodlist.naverApi.NaverClient;
import com.example.foodlist.wishlist.dto.WishListEntityDto;
import com.example.foodlist.wishlist.entity.WishListEntity;
import com.example.foodlist.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListEntityDto search(String query){
        //지역 검색(지역검색을 해서 있을 경우 이미지 결과가 있는지 없는지도 체크한다)
            var searchLocalReq = new SearchLocalReq();
            searchLocalReq.setQuery(query);

            var searchLocalRes = naverClient.localSearch(searchLocalReq);
            //결과가 있을수도 있고 없을 수도 있따.
            if(searchLocalRes.getTotal() >0){   //검색된 데이터가 있을때이다. 그래서 이경우는 null이 발생할 수 없다.
                var item = searchLocalRes.getItems().stream().findFirst().get();        //첫번째 아이템을 꺼낸다.

                var imageQuery = item.getTitle().replaceAll("<[^>]*>","");  //괄호랑 이상한 문자들은 다 없에버린다.
                var searchImageReq = new SearchImageReq();
                searchImageReq.setQuery(imageQuery);    //바꾼 값을 넣음 -- 이 결과도 있는지 검사해야한다. NaverClient에 메소드에 Request를 보낼것이다.
                //지역결과를 받고 지역결과에 제목과 일치하는 이미지를 뽑아낸다
                var searchImageRes = naverClient.imageSearch(searchImageReq);
                if(searchImageRes.getTotal() > 0){  //이미지에 요청을 보냈을때 이미지 콘텐츠들이 있을 경우
                    var imageitem = searchImageRes.getItems().stream().findFirst().get();
                    //findFirst() 메소드 반환타입이 Optional이다

                    var result = new WishListEntityDto();
                    result.setTitle(item.getTitle());
                    result.setCategory(item.getCategory());
                    result.setAddress(item.getAddress());
                    result.setRoadAddress(item.getRoadAddress());
                    result.setHopageLink(item.getLink());
                    result.setImageLink(imageitem.getLink());

                    return result;
                }
            }
            return new WishListEntityDto(); //비어있는 값을 던진다
    }
    public WishListEntityDto add(WishListEntityDto wishListEntityDto) {
            var entity = dtoToEntity(wishListEntityDto);        //dto를 Entity로
            var saveEntity = wishListRepository.save(entity);   //저장하면 반환타입은 Entity 제네릭에 의해서
            return entityToDto(saveEntity); //그리고 Entitiy를 다시 DTO로 바꾼 값을 반환한다

    }

    private WishListEntity dtoToEntity(WishListEntityDto wishListEntityDto){    //dto 클래스를 Entity로 바꿔서 저장한다 둘에 차이는 MemoryDb를 상속받냐 안받냐차이다
                //dto를 Entitiy객체로
                var entity = new WishListEntity();
        entity.setIndex(wishListEntityDto.getIndex());
        entity.setTitle(wishListEntityDto.getTitle());
        entity.setCategory(wishListEntityDto.getCategory());
        entity.setAddress(wishListEntityDto.getAddress());
        entity.setRoadAddress(wishListEntityDto.getRoadAddress());
        entity.setHopageLink(wishListEntityDto.getHopageLink());
        entity.setImageLink(wishListEntityDto.getImageLink());
        entity.setVisit(wishListEntityDto.isVisit());
        entity.setVisitCount(wishListEntityDto.getVisitCount());
        entity.setLastVistDate(wishListEntityDto.getLastVistDate());

        return entity;
    }

    private WishListEntityDto entityToDto(WishListEntity wishListEntity){
                //entity를 dto객체로 바꾼다
        var dto = new WishListEntityDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHopageLink(wishListEntity.getHopageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVistDate(wishListEntity.getLastVistDate());

        return dto;

    }

    public List<WishListEntityDto> findAll() {

        return wishListRepository.listAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
        // 여기에 값이 Entitiy이기에 Dto로ㅓ 바꿔서 해준다. Collect는 List형태로 다시 바꿔준거
            //map은 함수를 사용하기 위해 사용함
    }
}



