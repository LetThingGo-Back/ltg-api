package com.letthinggo.ltgapi.domain.application.data.dto;

import com.letthinggo.ltgapi.domain.application.data.entity.AppAvailability;
import lombok.Builder;
import lombok.Data;

@Data
public class AppAvailabilityDto {
    private String availableDate;
    private String startTime;
    private String endTime;

    @Builder
    public AppAvailabilityDto(String availableDate, String startTime, String endTime) {
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static AppAvailabilityDto createDto(AppAvailability appAvailability){
        return AppAvailabilityDto.builder()
                .availableDate(appAvailability.getAvailableDate())
                .startTime(appAvailability.getStartTime())
                .endTime(appAvailability.getEndTime()).build();

    }
}
