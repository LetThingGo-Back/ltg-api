package com.letthinggo.ltgapi.domain.item.data.repository;

import com.letthinggo.ltgapi.domain.item.data.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
