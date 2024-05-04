package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.QUserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserResponseTestDto;
import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.letthinggo.ltgapi.data.entity.QUsers.users;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserResponseTestDto> search(UserRequestTestDto request) {
        return queryFactory
                .select(new QUserResponseTestDto(
                        users.id,
                        users.nickname,
                        users.email))
                .from(users)
                .where(nicknameEq(request.getNickname()),
                        emailEq(request.getEmail()))
                .fetch();
    }

    private BooleanExpression nicknameEq(String nickname) {
        return isEmpty(nickname) ? null : users.nickname.eq(nickname);
    }
    private BooleanExpression emailEq(String email) {
        return isEmpty(email) ? null : users.email.eq(email);
    }
}
