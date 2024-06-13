package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.CodeSearchRequest;
import com.letthinggo.ltgapi.data.dto.CodeSearchResponse;

public interface GroupCodeRepositoryCustom {
    CodeSearchResponse findAllByGroupCodeAndCode(String GroupCode, String Code, CodeSearchRequest codeRequest);
}

