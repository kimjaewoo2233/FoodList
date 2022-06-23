package com.example.foodlist.wishlist.repository;

import com.example.foodlist.db.MemoryDbRepositoryAbstract;
import com.example.foodlist.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Component;


@Component
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
