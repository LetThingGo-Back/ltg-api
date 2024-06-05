package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LOCATION")
public class Location extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID", nullable = false)
    private Long id;

    @Column(name = "ITEM_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @Column(name = "ADDRESS", nullable = false, length = 500)
    private String address;

    @Column(name = "DISTRICT", nullable = false, length = 30)
    private String district;

    @Column(name = "DONG", nullable = false, length = 30)
    private String dong;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "LATITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "LIGHTING_YN", nullable = false)
    private Character lightingYn;

    @Convert(converter = AvailabiltyCodeConverter.class)
    @Column(name = "AVAILABILITY_CODE", nullable = false)
    private AvailabiltyCode availabilityCode;

}