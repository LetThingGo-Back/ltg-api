package com.letthinggo.ltgapi.domain.sample.data.repository;

import com.letthinggo.ltgapi.domain.user.data.entity.Users;
import com.letthinggo.ltgapi.domain.user.data.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<Users, Long> , UserRepositoryCustom {
}
