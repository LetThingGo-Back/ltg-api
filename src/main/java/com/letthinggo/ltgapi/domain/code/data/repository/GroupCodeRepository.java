package com.letthinggo.ltgapi.domain.code.data.repository;

import com.letthinggo.ltgapi.domain.code.data.entity.GroupCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupCodeRepository extends JpaRepository<GroupCode, String>, GroupCodeRepositoryCustom {

}
