package com.letthinggo.ltgapi.domain.code.data.repository;

import com.letthinggo.ltgapi.domain.code.data.dto.*;
import com.letthinggo.ltgapi.domain.code.data.dto.CodeSearchRequest;
import com.letthinggo.ltgapi.domain.code.data.dto.CodeSearchResponse;
import com.letthinggo.ltgapi.domain.code.data.dto.GroupCodeSearchRequest;
import com.letthinggo.ltgapi.domain.code.data.dto.GroupCodeSearchResponse;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.letthinggo.ltgapi.domain.code.data.entity.QGroupCode.groupCode1;
import static com.letthinggo.ltgapi.domain.code.data.entity.QCode.code;

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
                        groupCodeUseYnEq("Y"),
                        codeRequest != null ? useYnEq(codeRequest.getUseYn()) : null,
                        codeRequest != null ? codeKorNameLike(codeRequest.getCodeKorName()) : null,
                        codeRequest != null ? codeEngNameLike(codeRequest.getCodeEngName()) : null,
                        codeRequest != null ? mngItem1Eq(codeRequest.getMngItem1()): null,
                        codeRequest != null ? mngItem2Eq(codeRequest.getMngItem2()): null,
                        codeRequest != null ? mngItem3Eq(codeRequest.getMngItem3()): null,
                        codeRequest != null ? mngItem4Eq(codeRequest.getMngItem4()): null
                )
                .orderBy(code.codeSeq.asc())
                .transform(GroupBy.groupBy(groupCode1.groupCode).list(new QCodeSearchResponse(
                        groupCode1.groupCode,
                        groupCode1.groupCodeName,
                        groupCode1.mngDes1,
                        groupCode1.mngDes2,
                        groupCode1.mngDes3,
                        groupCode1.mngDes4,
                        groupCode1.description,
                        groupCode1.useYn,
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

    @Override
    public List<GroupCodeSearchResponse> findAllByGroupCode(String groupCodeParam, GroupCodeSearchRequest groupCodeRequest) {
        return queryFactory
                .select(new QGroupCodeSearchResponse(
                        groupCode1.groupCode
                        ,groupCode1.groupCodeName
                        ,groupCode1.mngDes1
                        ,groupCode1.mngDes2
                        ,groupCode1.mngDes3
                        ,groupCode1.mngDes4
                        ,groupCode1.description
                        ,groupCode1.useYn
                ))
                .from(groupCode1)
                .where(
                        groupCodeEq(groupCodeParam),
                        groupCodeRequest!=null ? groupCodeUseYnEq(groupCodeRequest.getUseYn()) : null,
                        groupCodeRequest!=null ? groupCodeNameEq(groupCodeRequest.getGroupCodeName()) : null
                )
                .orderBy(groupCode1.groupCode.asc(), groupCode1.createdDate.asc())
                .fetch();
    }

    private BooleanExpression groupCodeEq(String groupCodeParam) {
        return groupCodeParam != null && !StringUtils.isBlank(groupCodeParam) ? groupCode1.groupCode.eq(groupCodeParam) : null;
    }

    private BooleanExpression groupCodeUseYnEq(String useYn) {
        return useYn != null && !StringUtils.isBlank(useYn) ? groupCode1.useYn.eq(useYn) : null;
    }

    private BooleanExpression groupCodeNameEq(String groupCodeName) {
        return groupCodeName != null && !StringUtils.isBlank(groupCodeName) ? groupCode1.groupCodeName.eq(groupCodeName) : null;
    }


    private BooleanExpression codeEq(String codeParam) {
        return codeParam != null && !StringUtils.isBlank(codeParam) ? code.codePk.code.eq(codeParam) : null;
    }

    private BooleanExpression useYnEq(String useYn) {
        return useYn != null && !StringUtils.isBlank(useYn) ? code.useYn.eq(useYn) : null;
    }

    private BooleanExpression codeKorNameLike(String codeKorName) {
        return codeKorName != null && !StringUtils.isBlank(codeKorName) ? code.codeKorName.like(codeKorName + "%") : null;
    }

    private BooleanExpression codeEngNameLike(String codeEngName) {
        return codeEngName != null && !StringUtils.isBlank(codeEngName) ? code.codeEngName.like(codeEngName + "%") : null;
    }

    private BooleanExpression mngItem1Eq(String mngItem1) {
        return mngItem1 != null && !StringUtils.isBlank(mngItem1) ? code.mngItem1.eq(mngItem1) : null;
    }

    private BooleanExpression mngItem2Eq(String mngItem2) {
        return mngItem2 != null && !StringUtils.isBlank(mngItem2) ? code.mngItem2.eq(mngItem2) : null;
    }

    private BooleanExpression mngItem3Eq(String mngItem3) {
        return mngItem3 != null && !StringUtils.isBlank(mngItem3) ? code.mngItem3.eq(mngItem3) : null;
    }

    private BooleanExpression mngItem4Eq(String mngItem4) {
        return mngItem4 != null && !StringUtils.isBlank(mngItem4) ? code.mngItem4.eq(mngItem4) : null;
    }
}
