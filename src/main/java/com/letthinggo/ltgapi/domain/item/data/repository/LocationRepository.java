package com.letthinggo.ltgapi.domain.item.data.repository;

import com.letthinggo.ltgapi.domain.item.data.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
