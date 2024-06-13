package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.*;

public interface CodeService {
    GroupCodeCreateResponse createGroupCode(GroupCodeCreateRequest groupCodeCreateRequest)throws Exception;

    int createCode(CodeCreateRequest codeCreateRequest)throws Exception;

    CodeSearchResponse retrieveCode(String groupCode, String code, CodeSearchRequest codeRequest)throws Exception;
}
