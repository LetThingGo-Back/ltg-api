package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AvailbilityRepository extends JpaRepository<Availability,Long> , AvailbilityRepositoryCustom {
}
