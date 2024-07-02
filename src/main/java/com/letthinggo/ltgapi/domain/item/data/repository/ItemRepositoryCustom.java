package com.letthinggo.ltgapi.domain.item.data.repository;

import com.letthinggo.ltgapi.domain.item.data.entity.item.Item;

public interface ItemRepositoryCustom{
    Item findAllById(Long itemId);
}
