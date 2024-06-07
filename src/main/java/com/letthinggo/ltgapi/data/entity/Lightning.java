package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}