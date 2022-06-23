package com.example.foodlist.wishlist.entity;

import com.example.foodlist.db.MemoryDbEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class WishListEntity extends MemoryDbEntity {
    private String title; //음식명,장소명
    private String categotry; //카테고리
    private String address;		//주소
    private String readAddress;	//도로명 주소
    private String hopageLink;	//홈페이지주소
    private String imageLink; //음식,가게 임지ㅣ 주소
    private boolean isVisit; //방문여부
    private int visitCount; //방문 카운트
    private LocalDateTime lastVistDate; //마지막 방문일자
}
