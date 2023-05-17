package com.jwland.jwland.domain.account.service;

import com.jwland.jwland.domain.account.repository.SchoolRepository;
import com.jwland.jwland.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository schoolRepository;

    @Override
    public School findSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학교입니다."));
    }
}
