package com.letthinggo.ltgapi.domain.auth.data.repository;

import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLogin;
import com.letthinggo.ltgapi.domain.auth.data.entity.SocialLoginCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AuthRepository extends JpaRepository<SocialLogin, Long>{

    SocialLogin findBySocialCodeAndExternalId(@Param("socialCode") SocialLoginCode socialCode, @Param("externalId") String externalId);
}
