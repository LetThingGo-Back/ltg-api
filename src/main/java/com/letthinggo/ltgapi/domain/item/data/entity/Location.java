package com.letthinggo.ltgapi.domain.item.data.entity;

import com.letthinggo.ltgapi.domain.item.data.dto.ItemDto;
import com.letthinggo.ltgapi.domain.item.data.entity.availability.AvailabiltyCode;
import com.letthinggo.ltgapi.domain.item.data.entity.availability.AvailabiltyCodeConverter;
import com.letthinggo.ltgapi.domain.item.data.entity.item.Item;
import com.letthinggo.ltgapi.global.audit.BaseDateTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LOCATION")
public class Location extends BaseDateTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID", nullable = false)
    private Long id;

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

    @Column(name = "LIGHTNING_YN", nullable = false)
    private char lightningYn;

    @Convert(converter = AvailabiltyCodeConverter.class)
    @Column(name = "AVAILABILITY_CODE", nullable = false)
    private AvailabiltyCode availabilityCode;

    @Builder
    public Location (Item item, String address,String district, String dong, String description, BigDecimal latitude, BigDecimal longitude, char lightningYn, AvailabiltyCode availabilityCode) {
        this.item = item;
        this.address = address;
        this.district = district;
        this.dong = dong;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lightningYn = lightningYn;
        this.availabilityCode = availabilityCode;
    }

    //TODO 추후 수정예정
    public static List<Location> createLocation(ItemDto.CreateRequest createRequest , Item item){
        return createRequest.getAddrAndAvailList().stream()
                .map(dto -> Location.builder()
                        .item(item)
                        .address(dto.getAddress())
                        .district(dto.getDistrict())
                        .dong(dto.getDong())
                        .description(dto.getAddressDescription())
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .lightningYn(dto.getLightningYn())
                        .availabilityCode(AvailabiltyCode.fromCode(dto.getAvailabilityCode()))
                        .build())
                .collect(Collectors.toList());
    }
}