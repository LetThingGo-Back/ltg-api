package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.dto.UserRequestTestDto;

import com.letthinggo.ltgapi.data.entity.Application;

import java.util.List;

public interface ApplicationRepositoryCustom {
    List<Application> findAllById(Long applicationId);
}
