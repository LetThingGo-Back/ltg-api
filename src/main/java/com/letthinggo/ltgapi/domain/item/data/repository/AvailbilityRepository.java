package com.letthinggo.ltgapi.domain.item.data.repository;

import com.letthinggo.ltgapi.domain.item.data.entity.availability.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailbilityRepository extends JpaRepository<Availability,Long> , AvailbilityRepositoryCustom {
}
