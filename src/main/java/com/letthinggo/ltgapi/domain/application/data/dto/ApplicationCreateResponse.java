package com.letthinggo.ltgapi.domain.application.data.dto;

import com.letthinggo.ltgapi.domain.application.data.entity.AppAvailability;
import com.letthinggo.ltgapi.domain.application.data.entity.Application;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class ApplicationCreateResponse {
    private String address;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String memo;
    private List<AppAvailabilityDto> appAvailabilities = new ArrayList<>();

    @Builder
    public ApplicationCreateResponse(String address, String description, BigDecimal latitude, BigDecimal longitude, String memo, List<AppAvailabilityDto> appAvailabilities) {
        this.address = address;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memo = memo;
        this.appAvailabilities = appAvailabilities;
    }
    // TODO: 추후에 다시 변경
    public static ApplicationCreateResponse createResponse(Application application, List<AppAvailability> appAvailabilities){
        return ApplicationCreateResponse.builder()
                .memo(application.getMemo())
                .appAvailabilities(appAvailabilities.stream().map(AppAvailabilityDto::createDto)
                                                                             .collect(toList()))
                .build();
    }
}
