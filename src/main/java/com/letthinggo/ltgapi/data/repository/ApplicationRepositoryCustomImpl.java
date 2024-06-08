package com.letthinggo.ltgapi.data.repository;


import com.letthinggo.ltgapi.data.entity.Application;
import com.letthinggo.ltgapi.data.entity.QItem;
import com.letthinggo.ltgapi.data.entity.QLocation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.letthinggo.ltgapi.data.entity.QAppAvailability.appAvailability;
import static com.letthinggo.ltgapi.data.entity.QApplication.application;
import static com.letthinggo.ltgapi.data.entity.QItem.*;
import static com.letthinggo.ltgapi.data.entity.QLocation.*;


@RequiredArgsConstructor
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Application> findAllById(Long applicationId) {
        return queryFactory
                .selectFrom(application)
//                .innerJoin(item)
//                .fetchJoin()
//                .innerJoin(location)
//                .fetchJoin()
                .innerJoin(application.appAvailabilities, appAvailability)
                .fetchJoin()
                .where(application.id.eq(applicationId))
                .fetch();
    }
}
