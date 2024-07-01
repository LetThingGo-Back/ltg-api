package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
