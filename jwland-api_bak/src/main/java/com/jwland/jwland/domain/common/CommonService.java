package com.jwland.jwland.domain.common;

import com.jwland.jwland.constant.CommonCode;
import com.jwland.jwland.dto.EnumDto;
import com.jwland.jwland.entity.DetailCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommonService {

    private final DetailCodeRepository detailCodeRepository;
    private final GroupCodeRepository groupCodeRepository;


    public List<EnumDto> getSchools() {
        List<DetailCode> detailCodes = detailCodeRepository.findDetailCodeByGroupCode(CommonCode.GroupCode.SCHOOL_CODE.getCode());
        return detailCodes.stream()
                .map(detailCode -> EnumDto.builder()
                        .name(detailCode.getName())
                        .code(detailCode.getCode())
                        .build()
                ).collect(Collectors.toList());
    }
}
