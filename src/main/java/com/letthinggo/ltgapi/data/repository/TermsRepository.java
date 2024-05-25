package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TermsRepository extends JpaRepository<Terms, Long> {
    List<Terms> findByUseYn(@Param("useYn") String userYn);
}
