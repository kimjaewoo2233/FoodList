package com.example.foodlist.db;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemoryDbEntity {
    protected Integer index;    //int로 할 경우 디폴트 값이 0이고 db에 0값이 들어가면 안되기에 Intger를 사용한다
}
