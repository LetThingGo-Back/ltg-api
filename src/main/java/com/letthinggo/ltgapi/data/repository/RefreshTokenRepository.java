package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.RefreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(@Param("userId")Long userId);
}
