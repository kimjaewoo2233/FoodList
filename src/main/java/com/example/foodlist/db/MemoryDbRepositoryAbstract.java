package com.example.foodlist.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepository<T>{
        private final List<T> db = new ArrayList<>();
        private int index = 0;


    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        //filter  Predicate가 true인 모든 요소를 포함하는 스트림을 반환하는 메서드
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else{      //데이터가 있는경우
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
            Optional<T> optionalEntity =
                        db.stream().filter(it -> it.getIndex() == index).findFirst();
            if(optionalEntity.isPresent()){ //true이면 값이 있다
                    db.remove(optionalEntity.get());
            }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
