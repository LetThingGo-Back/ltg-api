package com.letthinggo.ltgapi.domain.application.service;


import com.letthinggo.ltgapi.domain.application.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.domain.application.data.dto.ApplicationCreateResponse;

public interface ApplicationService {

    public ApplicationCreateResponse createApplication(ApplicationCreateRequest request, Long userId);
}
