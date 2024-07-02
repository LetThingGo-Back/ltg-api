package com.letthinggo.ltgapi.domain.item.service;

import com.letthinggo.ltgapi.domain.item.data.dto.ItemDto;

public interface ItemService {
    public ItemDto.CreateResponse addItem(Long userId, ItemDto.CreateRequest createRequest);
}
