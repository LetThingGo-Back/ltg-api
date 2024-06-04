package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "AVAILABILITY")
public class Availability extends BaseDateTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_ID", nullable = false)
    private Long id;

    @Column(name = "LOCATION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "DAY_OF_WEEK", nullable = false)
    private Character dayOfWeek;

    @Column(name = "START_TIME", length = 4)
    private String startTime;

    @Column(name = "END_TIME", length = 4)
    private String endTime;

}