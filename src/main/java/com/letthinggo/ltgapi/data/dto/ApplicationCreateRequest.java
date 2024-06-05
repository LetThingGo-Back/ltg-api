package com.letthinggo.ltgapi.data.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationCreateRequest {
    private Long userId;
    private Long locationId;
    private Long ItemId;
    private String memo;
    private List<AppAvailabilityDto> appAvailabilities = new ArrayList<>();
}
