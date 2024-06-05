package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.ApplicationCreateRequest;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
public class Application extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="APPLICATION_ID")
    private Long id;
    
    // TODO: 추후에 다시 수정
    //private Item item;
    //private Location location;
    @Column(name="ITEM_ID")
    private Long itemId;

    @Column(name="LOCATION_ID")
    private Long locationId;

    @Column(name="APPLICANT_ID")
    private Long applicantId;

    @Column(name="MEMO")
    private String memo;

    @Column(name="DEL_YN")
    private String delYn;

    @OneToMany(mappedBy = "application")
    private List<AppAvailability> appAvailabilities = new ArrayList<>();

    @Builder
    public Application(Long itemId, Long locationId, Long applicantId, String memo, String delYn) {
        this.itemId = itemId;
        this.locationId = locationId;
        this.applicantId = applicantId;
        this.memo = memo;
        this.delYn = delYn;
    }

    public static Application createApplication(ApplicationCreateRequest request, Long userId){
        return Application.builder()
                .itemId(request.getItemId())
                .locationId(request.getLocationId())
                .applicantId(userId)
                .memo(request.getMemo())
                .delYn("N")
                .build();
    }
}
