package com.jwland.jwland.domain.lesson.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AccountIdsEnrollingLessonDto {

    @NotNull(message = "accountIds 는 필수값입니다.")
    @Size(min = 1)
    private List<Long> accountIds;
}
