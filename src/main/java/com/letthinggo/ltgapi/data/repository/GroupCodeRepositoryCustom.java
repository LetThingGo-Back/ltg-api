package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.CodeSearchRequest;
import com.letthinggo.ltgapi.data.dto.CodeSearchResponse;
import com.letthinggo.ltgapi.data.dto.GroupCodeSearchRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeSearchResponse;
import com.letthinggo.ltgapi.data.entity.GroupCode;

import java.util.List;

public interface GroupCodeRepositoryCustom {
    CodeSearchResponse findAllByGroupCodeAndCode(String GroupCode, String Code, CodeSearchRequest codeRequest);
    List<GroupCodeSearchResponse> findAllByGroupCode(String GroupCode, GroupCodeSearchRequest groupCodeRequest);
}

