package com.jwland.jwland.domain.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LessonAttendanceDto {

    private List<@Valid Attendance> attendance;

    public List<Long> getAccountIds(){
        return this.attendance.stream()
                .map(a -> a.getAccountId())
                .collect(Collectors.toList());
    }

    public String getAttendanceStatusByAccountId(Long accountId){
        return this.attendance.stream()
                .filter(a -> accountId.equals(a.getAccountId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 accountId가 존재합니다. - %s", accountId  + "")))
                .getAttendanceStatus();
    }

}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Attendance{
    @NotNull
    private Long accountId;
    @NotNull
    private String attendanceStatus;

}
