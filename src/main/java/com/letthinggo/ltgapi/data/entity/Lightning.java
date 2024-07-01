package com.letthinggo.ltgapi.data.entity;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.letthinggo.ltgapi.data.dto.ItemDto;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LIGHTNING")
public class Lightning extends BaseDateTimeBy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIGHTNING_ID", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "LIGHTING_DATE", nullable = false, length = 8)
    private String lightingDate;

    @Column(name = "START_TIME")
    private String startTime;

    @Builder
    public Lightning(Location location, String lightingDate, String startTime) {
        this.location = location;
        this.lightingDate = lightingDate;
        this.startTime = startTime;
    }

    //TODO 추후 수정예정
    public static Lightning createLightning(ItemDto.LightningDto createRequest, Location location) {
        return Lightning.builder()
                .location(location)
                .lightingDate(createRequest.getLightingDate())
                .startTime(createRequest.getStartTime())
                .build();
    }
}