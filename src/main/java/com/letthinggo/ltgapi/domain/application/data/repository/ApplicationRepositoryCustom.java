package com.letthinggo.ltgapi.domain.application.data.repository;

import com.letthinggo.ltgapi.domain.application.data.entity.Application;

import java.util.List;

public interface ApplicationRepositoryCustom {
    List<Application> findAllById(Long applicationId);
}
