package com.letthinggo.ltgapi.domain.code.data.repository;

import com.letthinggo.ltgapi.domain.code.data.dto.CodeSearchRequest;
import com.letthinggo.ltgapi.domain.code.data.dto.CodeSearchResponse;
import com.letthinggo.ltgapi.domain.code.data.dto.GroupCodeSearchRequest;
import com.letthinggo.ltgapi.domain.code.data.dto.GroupCodeSearchResponse;

import java.util.List;

public interface GroupCodeRepositoryCustom {
    CodeSearchResponse findAllByGroupCodeAndCode(String GroupCode, String Code, CodeSearchRequest codeRequest);
    List<GroupCodeSearchResponse> findAllByGroupCode(String GroupCode, GroupCodeSearchRequest groupCodeRequest);
}

