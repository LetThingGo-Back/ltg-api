package com.letthinggo.ltgapi.service;


import com.letthinggo.ltgapi.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.data.dto.ApplicationCreateResponse;

public interface ApplicationService {

    public ApplicationCreateResponse createApplication(ApplicationCreateRequest request);
}
