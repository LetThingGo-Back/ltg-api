package com.letthinggo.ltgapi.data.dto;

import com.letthinggo.ltgapi.data.entity.AppAvailability;
import lombok.Builder;
import lombok.Data;

@Data
public class AppAvailabilityDto {
    private String availableDate;
    private String startDate;
    private String endDate;

    @Builder
    public AppAvailabilityDto(String availableDate, String startDate, String endDate) {
        this.availableDate = availableDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static AppAvailabilityDto createDto(AppAvailability appAvailability){
        return AppAvailabilityDto.builder()
                .availableDate(appAvailability.getAvailableDate())
                .startDate(appAvailability.getStartDate())
                .endDate(appAvailability.getEndDate()).build();

    }
}
