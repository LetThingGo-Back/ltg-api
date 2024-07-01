package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.ItemDto;

public interface ItemService {
    public ItemDto.CreateResponse addItem(Long userId, ItemDto.CreateRequest createRequest);
}
