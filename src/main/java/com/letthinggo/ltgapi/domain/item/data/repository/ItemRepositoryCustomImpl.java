package com.letthinggo.ltgapi.domain.item.data.repository;

import com.letthinggo.ltgapi.domain.item.data.entity.item.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
