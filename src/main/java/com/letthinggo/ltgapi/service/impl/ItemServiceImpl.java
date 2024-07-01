package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.ItemDto;
import com.letthinggo.ltgapi.data.entity.*;
import com.letthinggo.ltgapi.data.repository.*;
import com.letthinggo.ltgapi.exception.UserNotFoundException;
import com.letthinggo.ltgapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.letthinggo.ltgapi.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ItemAttachRepository itemAttachRepository;
    private final LocationRepository locationRepository;
    private final AvailbilityRepository availabilityRepository;
    private final LightningRepository lightningRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    @Transactional
    @Override
    public ItemDto.CreateResponse addItem(Long userId, ItemDto.CreateRequest createRequest) {

        // User 조회
        Optional<Users> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        // Category 조회
        Category category = categoryRepository.findBycategoryId(createRequest.getCategory());

        // 1.ITEM 등록
        Item itemIn = Item.createItem(createRequest, user.get(), category);
        itemRepository.save(itemIn);

        // 2. ITEM ATTACH  등록
        List<ItemAttach> attachListIn  = ItemAttach.createItemAttach(createRequest.getItemAttachList(),itemIn);
        itemAttachRepository.saveAll(attachListIn);

        // 3. LOCATION 등록
        List<Location> locationsIn = Location.createLocation(createRequest,itemIn);
        locationRepository.saveAll(locationsIn);

        // 4. AVAILABILTY 등록 TODO 추후 소스수정 예정
//        for(ItemDto.AddrAndAvailDto addrAndAvailDto : createRequest.getAddrAndAvailList()) {
//            List<ItemDto.AvailabiltyDto> ailList = addrAndAvailDto.getAvailabiltyList();
//            List<Availability> availabilityListIn = new ArrayList<>();
//
//            for(int i = 0 ; i < ailList.size() ; i++) {
//                System.out.println("location :: "+ locationsIn.get(i).getId());
//                 availabilityListIn = Availability.createAvailability(ailList, locationsIn.get(i));
//            }
//                availabilityRepository.saveAll(availabilityListIn);
//
//        }

        // 5. LIGHTNING 등록
        List<Lightning> lightningsIn = new ArrayList<Lightning>();

        //AddrAndAvailDto.LightningYn == 'Y'인거만 추출
        List<ItemDto.AddrAndAvailDto> lightingList =  createRequest.getAddrAndAvailList().stream()
                .filter(dto -> 'Y' == dto.getLightningYn())
                .collect(Collectors.toList());

        for(int i = 0 ; i < lightingList.size() ; i++){
            lightningsIn.add(Lightning.createLightning(lightningDateTime(),locationsIn.get(i)));
        }
        lightningRepository.saveAll(lightningsIn);

//        return CreateResponse.toCreateResponseDto(itemIn,attachListIn,locationsIn,lightningIn);
        return null;
    }

    private ItemDto.LightningDto lightningDateTime(){

        // 현재 날짜 구하기
        String lightingDate = LocalDate.now().format(DATE_FORMATTER);

        // 현재 시간 구하기
        LocalTime currentTime = LocalTime.now();

        //번개거래 시작시간 구하기
        String startTime = "";

        if(currentTime.getMinute() >= 40) {
            startTime = currentTime.truncatedTo(ChronoUnit.HOURS)
                    .plusHours(1)
                    .format(TIME_FORMATTER);
        }else{
            startTime = currentTime.format(TIME_FORMATTER);
        }

        return new ItemDto.LightningDto(lightingDate, startTime);
    }

}
