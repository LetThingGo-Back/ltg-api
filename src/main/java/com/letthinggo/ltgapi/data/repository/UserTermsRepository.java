package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.UserTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTerms, Long> {
}
