package com.letthinggo.ltgapi.domain.application.data.repository;


import com.letthinggo.ltgapi.domain.application.data.entity.Application;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.letthinggo.ltgapi.domain.application.data.entity.QAppAvailability.appAvailability;
import static com.letthinggo.ltgapi.domain.application.data.entity.QApplication.application;


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
