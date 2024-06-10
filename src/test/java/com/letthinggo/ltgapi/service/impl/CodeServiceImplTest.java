package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateResponse;
import com.letthinggo.ltgapi.data.entity.GroupCode;
import com.letthinggo.ltgapi.data.repository.GroupCodeRepository;
import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CodeServiceImplTest {
    @Autowired
    CodeService codeService;
    @Autowired
    GroupCodeRepository groupCodeRepository;

    @Test
    public void 그룹코드생성() throws Exception{
        //given
        GroupCodeCreateRequest groupCodeRequest = createGroupCode();

        //when
        GroupCodeCreateResponse groupCodeCreateResponse = codeService.createGroupCodes(groupCodeRequest);

        //then
        Optional<GroupCode> groupCode = groupCodeRepository.findById(groupCodeCreateResponse.getGroupCode());
        assertEquals("그룹코드는",  groupCode.get().getGroupCode(), groupCodeCreateResponse.getGroupCode());
        assertEquals("샤용여부는",  groupCode.get().getUseYn(), groupCodeCreateResponse.getUseYn());
    }

    @Test
    public void 중복_그룹코드_생성_에러() throws Exception{
        //given
        GroupCodeCreateRequest gruopCode1 = createGroupCode();
        GroupCodeCreateRequest groupCode2 = createGroupCode();

        //when
        codeService.createGroupCodes(gruopCode1);

        assertThrows(CommonException.class, () -> {
            codeService.createGroupCodes(groupCode2);
        });
    }

    public GroupCodeCreateRequest createGroupCode(){
        return GroupCodeCreateRequest.builder()
                .groupCode("TEST03")
                .groupCodeName("테스트코드3")
                .useYn("Y")
                .build();
    }
}