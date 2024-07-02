package com.letthinggo.ltgapi.domain.terms.data.repository;

import com.letthinggo.ltgapi.domain.terms.data.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface TermsRepository extends JpaRepository<Terms, Long> {
    List<Terms> findByUseYn(@Param("useYn") String useYn);
}
