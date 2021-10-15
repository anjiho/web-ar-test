package com.sk.ar.web.test.config;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResultObjectDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        log.error("error {} " + errorMessage);
        ApiResultObjectDto apiResultObjectDTO = new ApiResultObjectDto(errorMessage, 801);
        return ResponseEntity.ok(apiResultObjectDTO);
    }
}
