package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.GroupCode;

import java.util.List;

public interface GroupCodeRepositoryCustom {
    List<GroupCode> findAllByGroupCodeAndCode(String GroupCode, String Code);
}

