package com.letthinggo.ltgapi.service;

import com.letthinggo.ltgapi.data.dto.CodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.CodeDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.letthinggo.ltgapi.exception.ErrorCode.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CodeServiceTest {
    @Autowired
    CodeService codeService;
    @Autowired
    GroupCodeRepository groupCodeRepository;

    @Test
    public void 그룹코드생성() throws Exception{
        //given
        GroupCodeCreateRequest groupCodeRequest = createGroupCode();

        //when
        GroupCodeCreateResponse groupCodeCreateResponse = codeService.createGroupCode(groupCodeRequest);

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
        codeService.createGroupCode(gruopCode1);

        Throwable exception =assertThrows(CommonException.class, () -> {
            codeService.createGroupCode(groupCode2);
        });

        assertEquals(DUPLICATE_GROUP_CODE.getMessage(), exception.getMessage());
    }

    @Test
    public void 중복_요청공통코드_생성_에러() throws Exception{
        //given
        CodeCreateRequest codeRequest = codeCreateRequest2();

        //when
        Throwable exception = assertThrows(CommonException.class, () -> {
            codeService.createCode(codeRequest);
        });

        assertEquals(INVALID_CODE.getMessage(), exception.getMessage());
    }

    @Test
    public void 중복_공통코드_생성_에러() throws Exception{
        //given
        CodeCreateRequest codeRequest = codeCreateRequest();

        //when
        Throwable exception = assertThrows(CommonException.class, () -> {
            codeService.createCode(codeRequest);
        });

        assertEquals(DUPLICATE_CODE.getMessage(), exception.getMessage());
    }


    public GroupCodeCreateRequest createGroupCode(){
        return GroupCodeCreateRequest.builder()
                .groupCode("TEST99")
                .groupCodeName("테스트코드99")
                .useYn("Y")
                .build();
    }

    public CodeCreateRequest codeCreateRequest(){
        List<CodeDto> codeDtoList = new ArrayList<>();
        codeDtoList.add(CodeDto.builder()
                .code("E")
                .codeKorName("테스트E")
                .build());

        return CodeCreateRequest.builder().groupCode("TEST01")
                .codes(codeDtoList)
                .build();
    }

    public CodeCreateRequest codeCreateRequest2(){
        List<CodeDto> codeDtoList = new ArrayList<>();
        codeDtoList.add(CodeDto.builder()
                .code("E")
                .codeKorName("테스트1")
                .build());
        codeDtoList.add(CodeDto.builder()
                .code("E")
                .codeKorName("테스트2")
                .build());

        return CodeCreateRequest.builder().groupCode("TEST01")
                .codes(codeDtoList)
                .build();
    }
}