package com.letthinggo.ltgapi.domain.application.data.repository;

import com.letthinggo.ltgapi.domain.application.data.entity.AppAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppAvailabilityRepository extends JpaRepository<AppAvailability, Long> {
}
