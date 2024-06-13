package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.*;
import com.letthinggo.ltgapi.data.entity.Code;
import com.letthinggo.ltgapi.exception.CommonException;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.letthinggo.ltgapi.data.entity.QGroupCode.groupCode1;
import static com.letthinggo.ltgapi.data.entity.QCode.code;
import static com.letthinggo.ltgapi.data.entity.QUsers.users;
import static com.letthinggo.ltgapi.exception.ErrorCode.GROUP_CODE_NOT_FOUND;
//import static com.querydsl.core.group.GroupBy.groupBy;
//import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.group.GroupBy.groupBy;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import static com.querydsl.core.group.GroupBy.list;

@RequiredArgsConstructor
public class GroupCodeRepositoryCustomImpl implements GroupCodeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public CodeSearchResponse findAllByGroupCodeAndCode(String groupCodeParam, String codeParam, CodeSearchRequest codeRequest) {
        List<CodeSearchResponse> result = queryFactory
                .from(groupCode1)
                .leftJoin(code).on(groupCode1.eq(code.codePk.groupCode))
                .where(
                        groupCodeEq(groupCodeParam),
                        codeEq(codeParam),
                        codeRequest != null ? useYnEq(codeRequest.getUseYn()) : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getCodeKorName()) ? code.codeKorName.like(codeRequest.getCodeKorName() + "%") : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getCodeEngName()) ? code.codeEngName.like(codeRequest.getCodeEngName() + "%") : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getMngItem1()) ? code.mngItem1.eq(codeRequest.getMngItem1()) : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getMngItem2()) ? code.mngItem2.eq(codeRequest.getMngItem2()) : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getMngItem3()) ? code.mngItem3.eq(codeRequest.getMngItem3()) : null,
                        codeRequest != null && !StringUtils.isBlank(codeRequest.getMngItem4()) ? code.mngItem4.eq(codeRequest.getMngItem4()) : null
                )
                .orderBy(code.codeSeq.asc())
                .transform(GroupBy.groupBy(groupCode1.groupCode).list(new QCodeSearchResponse(
                        groupCode1.groupCode,
                        groupCode1.groupCodeName,
                        list(new QCodeDto(code.codePk.code
                                        , code.codeKorName
                                        , code.codeEngName
                                        , code.mngItem1
                                        ,code.mngItem2
                                        ,code.mngItem3
                                        ,code.mngItem4
                                        ,code.description
                                        ,code.useYn
                                        ,code.codeSeq))
                )))
                ;
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    private BooleanExpression groupCodeEq(String groupCodeParam) {
        return groupCodeParam != null && !StringUtils.isBlank(groupCodeParam) ? groupCode1.groupCode.eq(groupCodeParam) : null;
    }
    private BooleanExpression codeEq(String codeParam) {
        return codeParam != null && !StringUtils.isBlank(codeParam) ? code.codePk.code.eq(codeParam) : null;
    }
    private BooleanExpression useYnEq(String useYn) {
        return useYn != null && !StringUtils.isBlank(useYn) ? code.useYn.eq(useYn) : null;
    }
}
