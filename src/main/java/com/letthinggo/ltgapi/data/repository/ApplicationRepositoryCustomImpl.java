package com.letthinggo.ltgapi.data.repository;


import com.letthinggo.ltgapi.data.entity.Application;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.letthinggo.ltgapi.data.entity.QAppAvailability.appAvailability;
import static com.letthinggo.ltgapi.data.entity.QApplication.application;


@RequiredArgsConstructor
public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Application> findAllById(Long applicationId) {
        return queryFactory
                .selectFrom(application)
                .innerJoin(application.appAvailabilities, appAvailability)
                .where(application.id.eq(applicationId))
                .fetch();
    }
}
