package com.letthinggo.ltgapi.domain.item.data.entity.availability;

import com.letthinggo.ltgapi.domain.item.data.dto.ItemDto;
import com.letthinggo.ltgapi.domain.item.data.entity.Location;
import com.letthinggo.ltgapi.global.audit.BaseDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "AVAILABILITY")
public class Availability extends BaseDateTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "DAY_OF_WEEK")
    private DayOfWeek dayOfWeek;

    @Column(name = "START_TIME")
    private String startTime;

    @Column(name = "END_TIME")
    private String endTime;

    @Builder
    public Availability(Location location, DayOfWeek dayOfWeek, String startTime, String endTime) {
        this.location = location;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //TODO 추후 수정예정
    public static List<Availability> createAvailability(List<ItemDto.AvailabiltyDto> createRequest, Location location) {
        return createRequest.stream()
                .map(dto -> Availability.builder()
                        .location(location)
                        .dayOfWeek(DayOfWeek.MONDAY)
                        .startTime(dto.getStartTime())
                        .endTime(dto.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }

}