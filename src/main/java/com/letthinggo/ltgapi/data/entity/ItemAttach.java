package com.letthinggo.ltgapi.data.entity;

import com.letthinggo.ltgapi.data.dto.ItemDto;
import com.letthinggo.ltgapi.data.entity.common.BaseDateTimeBy;
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
@Table(name = "ITEM_ATTACH")
public class ItemAttach extends BaseDateTimeBy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ATTACH_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @Column(name = "ITEM_ATTACH_NAME")
    private String itemAttachName;

    @Column(name = "ITEM_ATTACH_SIZE")
    private Byte itemAttachSize;

    @Column(name = "ITEM_ATTACH_URL")
    private String itemAttachUrl;

    @Column(name = "ITEM_ATTACH_EXTS")
    private String itemAttachExts;

    @Column(name = "THUM_YN")
    private char thumYn;

    @Builder
    public ItemAttach(Item item, String itemAttachName, Byte itemAttachSize, String itemAttachUrl, String itemAttachExts, char thumYn) {
        this.item = item;
        this.itemAttachName = itemAttachName;
        this.itemAttachSize = itemAttachSize;
        this.itemAttachUrl = itemAttachUrl;
        this.itemAttachExts = itemAttachExts;
        this.thumYn = thumYn;
    }

    //TODO 추후 수정예정
    public static List<ItemAttach> createItemAttach(List<ItemDto.ItemAttachDto> createRequest, Item itemIn){
        return createRequest.stream()
                .map(dto -> ItemAttach.builder()
                        .item(itemIn)
                        .itemAttachName(dto.getItemAttachName())
                        .itemAttachSize(dto.getItemAttachSize())
                        .itemAttachUrl(dto.getItemAttachUrl())
                        .itemAttachExts(dto.getItemAttachExts())
                        .thumYn(dto.getThumYn())
                        .build())
                .collect(Collectors.toList());
    }
}
