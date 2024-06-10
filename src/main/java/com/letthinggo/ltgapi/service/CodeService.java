package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.CodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateResponse;

public interface CodeService {
    GroupCodeCreateResponse createGroupCode(GroupCodeCreateRequest groupCodeCreateRequest);

    int createCode(CodeCreateRequest codeCreateRequest);
}
