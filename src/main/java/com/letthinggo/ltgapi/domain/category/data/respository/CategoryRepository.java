package com.letthinggo.ltgapi.domain.category.data.respository;


import com.letthinggo.ltgapi.domain.category.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("select c from Category c where c.id=:categoryId")
    Category findBycategoryId(@Param("categoryId")Long categoryId);
}
