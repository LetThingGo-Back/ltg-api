package com.letthinggo.ltgapi.data.repository;

import com.letthinggo.ltgapi.data.entity.Item;

public interface ItemRepositoryCustom{
    Item findAllById(Long itemId);
}
