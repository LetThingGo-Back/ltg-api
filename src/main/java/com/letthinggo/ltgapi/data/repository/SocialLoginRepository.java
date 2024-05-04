package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long>{

    SocialLogin findBySocialCodeAndExternalId(@Param("socialCode") SocialLoginCode socialCode, @Param("externalId") String externalId);
}
