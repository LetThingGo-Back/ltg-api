package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.AppAvailabilityDto;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APP_AVAILABILITY")
public class AppAvailability extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="APP_AVAILABILITY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="APPLICATION_ID")
    private Application application;

    @Column(name="AVAILABLE_DATE")
    private String availableDate;

    @Column(name="START_TIME")
    private String startTime;

    @Column(name="END_TIME")
    private String endTime;

    @Convert(converter = ApplicationStatusConverter.class)
    @Column(name="APPLICATION_STATUS")
    private ApplicationStatus applicationStatus;

    @Builder
    public AppAvailability(Application application, String availableDate, String startTime, String endTime, ApplicationStatus applicationStatus) {
        this.application = application;
        this.availableDate = availableDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.applicationStatus = applicationStatus;
    }

    public static List<AppAvailability> createAppAvailability(List<AppAvailabilityDto> request, Application application) {
         return request.stream()
                 .map(dto -> AppAvailability.builder()
                         .application(application)
                         .availableDate(dto.getAvailableDate())
                         .startTime(dto.getStartTime())
                         .endTime(dto.getEndTime())
                         .applicationStatus(ApplicationStatus.WAITING)
                         .build())
                 .collect(Collectors.toList());
    }
}
