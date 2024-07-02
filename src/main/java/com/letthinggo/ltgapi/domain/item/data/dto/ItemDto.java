package com.letthinggo.ltgapi.domain.item.data.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;


@Data
public class ItemDto {

   @Getter
   @NoArgsConstructor
   public static class CreateRequest {
       private Long userId;
       private Long category;
       private String itemStatus;
       private String itemName;
       private String itemDescription;
       private List<ItemAttachDto> itemAttachList;
       private List<AddrAndAvailDto> addrAndAvailList;

       @Builder
       public CreateRequest(Long userId, Long category, String itemStatus, String itemName, String itemDescription, List<ItemAttachDto> itemAttachList, List<AddrAndAvailDto> addrAndAvailList) {
           this.userId = userId;
           this.category = category;
           this.itemStatus = itemStatus;
           this.itemName = itemName;
           this.itemDescription = itemDescription;
           this.itemAttachList = itemAttachList;
           this.addrAndAvailList = addrAndAvailList;
       }

   }

    @Getter
    public class CreateResponse {
        private Long category;
        private String itemStatus;
        private String itemName;
        private String itemDescription;
        private List<ItemAttachDto> itemAttachList;
        private List<AddrAndAvailDto> addrAndAvailList;

//        @Builder
//        public CreateResponse(Long category , String itemStatus, String itemName , String itemDescription, List<ItemAttachDto> itemAttachList, List<AddrAndAvailDto> addrAndAvailList){
//            this.category = category;
//            this.itemStatus = itemStatus;
//            this.itemName = itemName;
//            this.itemDescription = itemDescription;
//            this.itemAttachList = itemAttachList;
//            this.addrAndAvailList = addrAndAvailList;
//        }

//        public CreateResponse toCreateResponseDto (Item item) {
//            return
//        }
    }

   @Getter
   public static class ItemAttachDto  {
       private String itemAttachName;
       private Byte itemAttachSize;
       private String itemAttachUrl;
       private String itemAttachExts;
       private char thumYn;
   }

    @Getter
    public static class AddrAndAvailDto {
        private String address;
        private String addressDescription;
        private String district;
        private String dong;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private char lightningYn;
        private String availabilityCode;
        private List<AvailabiltyDto> availabiltyList;
    }

   @Getter
   public static class AvailabiltyDto {
       private String dayOfWeek;
       private String startTime;
       private String endTime;
   }

    @Getter
    public static class LightningDto {
        private String lightingDate;
        private String startTime;

        @Builder
        public LightningDto(String lightingDate, String startTime) {
            this.lightingDate = lightingDate;
            this.startTime = startTime;
        }
    }

}
