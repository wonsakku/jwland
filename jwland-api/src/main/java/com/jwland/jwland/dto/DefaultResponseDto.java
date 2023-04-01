package com.jwland.jwland.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponseDto <T> {

    private Integer statusCode;
    private String status;

    private T data;

    public DefaultResponseDto(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
    }

    public DefaultResponseDto(HttpStatus httpStatus, T data) {
        this(httpStatus);
        this.data = data;
    }



}




