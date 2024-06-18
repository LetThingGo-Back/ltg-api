package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Code;
import com.letthinggo.ltgapi.data.entity.CodePk;
import com.letthinggo.ltgapi.data.entity.GroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, CodePk> {
    @Query("select c from Code c where c.codePk.groupCode.groupCode = :groupCode")
    List<Code> findAllByGroupCode(@Param("groupCode") String groupCode);
}
