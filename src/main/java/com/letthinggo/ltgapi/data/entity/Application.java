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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="ITEM_ID")
//    private Item item;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="LOCATION_ID")
//    private Location location;
    @Column(name="ITEM_ID")
    private Long itemId; // TODO: 추후에 삭제
    @Column(name="LOCATION_ID")
    private Long locationId; // TODO: 추후에 삭제

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="APPLICANT_ID")
    private Users users;

    @Column(name="MEMO")
    private String memo;

    @Column(name="DEL_YN")
    private String delYn;

    @OneToMany(mappedBy = "application")
    private List<AppAvailability> appAvailabilities = new ArrayList<>();

    @Builder
    public Application(Item item, Location location, Long itemId, Long locationId, Users users, String memo, String delYn) {
//        this.item = item;
//        this.location = location;
        this.users = users;
        this.memo = memo;
        this.delYn = delYn;
        this.itemId = itemId; // TODO: 추후에 삭제
        this.locationId = locationId; // TODO: 추후에 삭제
    }
    // TODO: 추후에 삭제
    public static Application createApplication(ApplicationCreateRequest request, Users users){
        return Application.builder()
                .itemId(request.getItemId())
                .locationId(request.getLocationId())
                .users(users)
                .memo(request.getMemo())
                .delYn("N")
                .build();
    }

//    public static Application createApplication(Item item, Location location, Users users, String memo, String delYn){
//        return Application.builder()
//                .item(item)
//                .location(location)
//                .users(users)
//                .memo(request.getMemo())
//                .delYn(delYn)
//                .build();
//    }
}
