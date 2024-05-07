package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.RefreshToken;
import com.letthinggo.ltgapi.data.entity.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
