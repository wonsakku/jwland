package com.jwland.jwland.global;

import com.jwland.jwland.dto.DefaultResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<DefaultResponseDto> illegalArgumentAndStateExceptionHandler(RuntimeException e){
        log.error("error message : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body( new DefaultResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()) );
    }

}
