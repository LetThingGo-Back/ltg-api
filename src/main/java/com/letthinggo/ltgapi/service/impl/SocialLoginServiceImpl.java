package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.repository.SocialLoginRepository;
import com.letthinggo.ltgapi.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {
    private final SocialLoginRepository socialLoginRepository;

}
