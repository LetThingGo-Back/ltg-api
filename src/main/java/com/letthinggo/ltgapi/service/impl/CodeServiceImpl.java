package com.letthinggo.ltgapi.service.impl;

import com.letthinggo.ltgapi.data.dto.GroupCodeCreateRequest;
import com.letthinggo.ltgapi.data.dto.GroupCodeCreateResponse;
import com.letthinggo.ltgapi.data.entity.GroupCode;
import com.letthinggo.ltgapi.data.repository.CodeRepository;
import com.letthinggo.ltgapi.data.repository.GroupCodeRepository;
import com.letthinggo.ltgapi.exception.CommonException;
import com.letthinggo.ltgapi.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import static com.letthinggo.ltgapi.exception.ErrorCode.*;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;
    private final GroupCodeRepository groupCodeRepository;

    @Transactional
    @Override
    public GroupCodeCreateResponse createGroupCodes(GroupCodeCreateRequest groupCodeCreateRequest) {
        Optional<GroupCode> groupCodeOut =groupCodeRepository.findById(groupCodeCreateRequest.getGroupCode());
        if(groupCodeOut.isPresent()){
            throw new CommonException(GROUP_CODE_ALREADY_REGISTERED);
        }
        GroupCode groupCode= groupCodeRepository.save(GroupCode.createGroupCode(groupCodeCreateRequest));

        return GroupCodeCreateResponse.createResponse(groupCode);
    }
}
