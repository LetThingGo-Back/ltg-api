package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.GroupCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.letthinggo.ltgapi.data.entity.QGroupCode.groupCode1;
import static com.letthinggo.ltgapi.data.entity.QCode.code;


@RequiredArgsConstructor
public class GroupCodeRepositoryCustomImpl implements GroupCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupCode> findAllByGroupCodeAndCode(String groupCodeParam, String codeParam) {
        return queryFactory
                .selectFrom(groupCode1)
                .leftJoin(code)
                .on(groupCode1.eq(code.codePk.groupCode))
                .fetchJoin()
                .where(
                        !groupCodeParam.isBlank() ? groupCode1.groupCode.eq(groupCodeParam) : null,
                        !codeParam.isBlank() ? code.codePk.code.eq(codeParam) : null
                )
                .fetch();
    }
}
