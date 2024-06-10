package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Code;
import com.letthinggo.ltgapi.data.entity.CodePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, CodePk> {

}
