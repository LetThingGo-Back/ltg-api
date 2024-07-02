package com.letthinggo.ltgapi.domain.application.data.repository;

import com.letthinggo.ltgapi.domain.application.data.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ApplicationRepository extends JpaRepository<Application, Long> , ApplicationRepositoryCustom{
    @Query("select a from Application a where a.itemId=:itemId and a.users.id = :userId")
    List<Application> findByItemIdAndUserId(@Param("itemId")Long itemId, @Param("userId")Long UserId);
}
