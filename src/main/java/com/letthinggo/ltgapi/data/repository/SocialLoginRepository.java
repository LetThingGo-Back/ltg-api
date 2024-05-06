package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.SocialLogin;
import com.letthinggo.ltgapi.data.entity.SocialLoginCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long>{

    SocialLogin findBySocialCodeAndExternalId(@Param("socialCode") SocialLoginCode socialCode, @Param("externalId") String externalId);
    @Modifying
    @Query("UPDATE SocialLogin sl SET sl.refreshToken = :#{#param.refreshToken}, sl.expirationDate = :#{#param.expirationDate} WHERE sl.socialCode = :#{#param.socialCode} AND sl.externalId = :#{#param.externalId}")
    int updateRefreshTokenAndExpirationDate(@Param("param") SocialLogin socialLogin );
}
