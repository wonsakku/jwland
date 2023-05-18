package com.jwland.jwland.domain.subject.service;

import com.jwland.jwland.domain.subject.dto.SubjectDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeDto;
import com.jwland.jwland.domain.subject.dto.SubjectProblemTypeListDto;
import com.jwland.jwland.domain.subject.dto.SubjectsDto;
import com.jwland.jwland.entity.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AdminSubjectServiceFacade {

    private final AdminSubjectService adminSubjectService;
    private final SubjectProblemTypeService subjectProblemTypeService;

    public AdminSubjectServiceFacade(AdminSubjectService adminSubjectService, SubjectProblemTypeService subjectProblemTypeService) {
        this.adminSubjectService = adminSubjectService;
        this.subjectProblemTypeService = subjectProblemTypeService;
    }


    public List<SubjectsDto> findSubjects() {
        return adminSubjectService.findSubjects();
    }

    public void enrollSubject(SubjectDto subjectDto) {
        adminSubjectService.enrollSubject(subjectDto);
    }

    public void updateSubject(SubjectDto subjectDto) {
        adminSubjectService.updateSubject(subjectDto);
    }

    public void deleteSubject(Long subjectId) {
        adminSubjectService.deleteSubject(subjectId);
    }

    public void enrollProblemType(SubjectProblemTypeDto subjectProblemTypeDto) {
        final Subject subject = adminSubjectService.findSubjectById(subjectProblemTypeDto.getSubjectId());
        subjectProblemTypeService.enrollProblemType(subject, subjectProblemTypeDto);
    }

    public List<SubjectProblemTypeListDto> getProblemTypes(Long parentId, String problemClassification, Long subjectId) {
        return subjectProblemTypeService.getProblemTypes(parentId, problemClassification, subjectId);
    }

    public void updateProblemType(SubjectProblemTypeDto subjectProblemTypeDto) {
        subjectProblemTypeService.updateProblemType(subjectProblemTypeDto);
    }

}




