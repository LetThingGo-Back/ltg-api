package com.letthinggo.ltgapi.domain.application.service.impl;

import com.letthinggo.ltgapi.domain.application.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.domain.application.data.dto.ApplicationCreateResponse;
import com.letthinggo.ltgapi.domain.application.data.entity.AppAvailability;
import com.letthinggo.ltgapi.domain.application.data.entity.Application;
import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.letthinggo.ltgapi.domain.application.data.repository.AppAvailabilityRepository;
import com.letthinggo.ltgapi.domain.application.data.repository.ApplicationRepository;
import com.letthinggo.ltgapi.domain.user.data.repository.UserRepository;
import com.letthinggo.ltgapi.global.exception.CommonException;
import com.letthinggo.ltgapi.global.exception.UserNotFoundException;
import com.letthinggo.ltgapi.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.letthinggo.ltgapi.global.exception.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final AppAvailabilityRepository appAvailabilityRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ApplicationCreateResponse createApplication(ApplicationCreateRequest request, Long userId) {
        // 엔티티 조회
        Optional<Users> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        // TODO: Location, Item 엔티티 조회

        // 신청여부 확인
        List<Application> applicationsOut = applicationRepository.findByItemIdAndUserId(request.getItemId(), userId);
        if(!applicationsOut.isEmpty()){
            throw new CommonException(APPLICATION_ALREADY_REGISTERED);
        }
        // 1. 나눔 신청 등록
        Application applicationIn = Application.createApplication(request , user.get());
        applicationRepository.save(applicationIn);
        // 2. 나눔 가능 요청 시간 등록
        List<AppAvailability> appAvailabilitiesIn =  AppAvailability.createAppAvailability(request.getAppAvailabilities(), applicationIn);
        appAvailabilityRepository.saveAll(appAvailabilitiesIn);
        
        //TODO 추후에 다시 수정
        //3. 나눔 신청 정보 조회
        List<Application> applicationOut = applicationRepository.findAllById(applicationIn.getId());
        return ApplicationCreateResponse.createResponse(applicationOut.get(0), appAvailabilitiesIn);
    }
}
