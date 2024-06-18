package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.*;
import com.letthinggo.ltgapi.data.entity.Code;
import com.letthinggo.ltgapi.data.entity.GroupCode;
import com.letthinggo.ltgapi.data.repository.CodeRepository;
import com.letthinggo.ltgapi.data.repository.GroupCodeRepository;
import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.letthinggo.ltgapi.exception.ErrorCode.*;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;
    private final GroupCodeRepository groupCodeRepository;

    @Transactional
    @Override
    public GroupCodeCreateResponse createGroupCode(GroupCodeCreateRequest groupCodeCreateRequest) throws Exception {
        Optional<GroupCode> groupCodeOut =groupCodeRepository.findById(groupCodeCreateRequest.getGroupCode());
        if(groupCodeOut.isPresent()){
            // 동일한 그룹코드 값이 있을 때 예외 발생
            throw new CommonException(DUPLICATE_GROUP_CODE);
        }
        GroupCode groupCode= groupCodeRepository.save(GroupCode.createGroupCode(groupCodeCreateRequest));

        return GroupCodeCreateResponse.createResponse(groupCode);
    }

    @Transactional
    @Override
    public int createCode(CodeCreateRequest codeCreateRequest) throws Exception {
        // 1. 공통코드 중복 입력 체크
        validateCodes(codeCreateRequest.getCodes());

        // 2. 그룹코드 확인
        Optional<GroupCode> groupCodeOut =groupCodeRepository.findById(codeCreateRequest.getGroupCode());
        if(!groupCodeOut.isPresent()){
            throw new CommonException(GROUP_CODE_NOT_FOUND);
        }
        // 3. 공통코드 확인
        validateCodesFromEntities(groupCodeOut.get().getGroupCode(), codeCreateRequest.getCodes());

        // 4. 코드 생성
        List<Code> code = codeRepository.saveAll(Code.createCodes(codeCreateRequest, groupCodeOut.get()));
        return code.size();
    }

    @Override
    public CodeSearchResponse retrieveCode(String groupCode, String code, CodeSearchRequest codeRequest) throws Exception{
        // 1. 그룹코드 존재여부 확인
        Optional<GroupCode> groupCodeOut =groupCodeRepository.findById(groupCode);
        if(!groupCodeOut.isPresent()){
            throw new CommonException(GROUP_CODE_NOT_FOUND);
        }
        // 2. 공통코드 조회
        return groupCodeRepository.findAllByGroupCodeAndCode(groupCode, code, codeRequest);
    }

    @Override
    public List<GroupCodeSearchResponse> retrieveGroupCode(String groupCode, GroupCodeSearchRequest groupCodeRequest) {
        return groupCodeRepository.findAllByGroupCode(groupCode, groupCodeRequest);
    }

    public void validateCodes(List<CodeDto> requestCodes) {
        Set<String> codeSet = new HashSet<>();
        requestCodes.stream()
                .map(CodeDto::getCode)
                .forEach(code -> {
                    if (!codeSet.add(code)) {
                        throw new CommonException(INVALID_CODE);
                    }
                });
    }

    public void validateCodesFromEntities(String groupCode, List<CodeDto> requestCodes) {
        List<Code> codesOut = codeRepository.findAllByGroupCode(groupCode);
        List<String> requestCodeValues = requestCodes.stream()
                .map(CodeDto::getCode)
                .collect(Collectors.toList());

        codesOut.stream()
                .filter(code -> requestCodeValues.contains(code.getCodePk().getCode()))
                .findAny()
                .ifPresent(code -> {
                    throw new CommonException(DUPLICATE_CODE);
                });
    }
}
