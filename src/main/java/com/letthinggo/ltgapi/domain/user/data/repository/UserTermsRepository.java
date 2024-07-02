package com.letthinggo.ltgapi.domain.user.data.repository;

import com.letthinggo.ltgapi.domain.user.data.entity.UserTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTerms, Long> {
}
