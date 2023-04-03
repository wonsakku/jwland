package com.jwland.jwland.domain.global;

import com.jwland.jwland.dto.DefaultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<DefaultResponseDto> illegalArgumentAndStateExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body( new DefaultResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()) );
    }

}
