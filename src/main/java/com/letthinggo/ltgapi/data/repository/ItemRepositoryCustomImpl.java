package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.letthinggo.ltgapi.data.entity.QItem.item;
import static com.letthinggo.ltgapi.data.entity.QAttach.attach;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Item findAllById(Long itemId){
        return null;
//        return queryFactory
//                .selectFrom(item)
//                .innerJoin()
//                .fetch();
    };
}
