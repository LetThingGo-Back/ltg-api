package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> , ApplicationRepositoryCustom{
}
