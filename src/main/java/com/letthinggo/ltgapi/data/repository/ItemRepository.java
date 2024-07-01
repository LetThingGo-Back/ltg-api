package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
