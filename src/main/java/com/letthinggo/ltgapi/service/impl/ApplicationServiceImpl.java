package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.data.dto.ApplicationCreateResponse;
import com.letthinggo.ltgapi.data.entity.AppAvailability;
import com.letthinggo.ltgapi.data.entity.Application;
import com.letthinggo.ltgapi.data.repository.AppAvailabilityRepository;
import com.letthinggo.ltgapi.data.repository.ApplicationRepository;
import com.letthinggo.ltgapi.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final AppAvailabilityRepository appAvailabilityRepository;

    @Transactional
    @Override
    public ApplicationCreateResponse createApplication(ApplicationCreateRequest request, Long userId) {
        // 1. 나눔 신청 등록
        Application applicationIn = Application.createApplication(request , userId);
        applicationRepository.save(applicationIn);

        // 2. 나눔 가능 요청 시간 등록
        List<AppAvailability> appAvailabilitiesIn =  AppAvailability.createAppAvailability(request.getAppAvailabilities(), applicationIn);
        appAvailabilityRepository.saveAll(appAvailabilitiesIn);

        // 3. 나눔 신청 정보 조회
        List<Application> applicationOut = applicationRepository.findAllById(applicationIn.getId());
        return ApplicationCreateResponse.createResponse(applicationOut.get(0));
    }
}
