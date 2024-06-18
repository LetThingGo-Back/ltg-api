package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.*;

import java.util.List;

public interface CodeService {
    GroupCodeCreateResponse createGroupCode(GroupCodeCreateRequest groupCodeCreateRequest)throws Exception;

    int createCode(CodeCreateRequest codeCreateRequest)throws Exception;

    CodeSearchResponse retrieveCode(String groupCode, String code, CodeSearchRequest codeRequest)throws Exception;

    List<GroupCodeSearchResponse> retrieveGroupCode(String groupCode, GroupCodeSearchRequest groupCodeRequest);
}
