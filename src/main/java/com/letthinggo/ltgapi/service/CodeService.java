package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateResponse;

public interface CodeService {
    GroupCodeCreateResponse createGroupCodes(GroupCodeCreateRequest groupCodeCreateRequest);
}
