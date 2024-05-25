package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>, UserRepositoryCustom{
    boolean existsByNickname(String nickname);
}
