package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    List<User> findByNickname(String nickname);
}
