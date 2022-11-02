package com.example.Flexserver.exception;

import com.example.Flexserver.domain.response.Response;
import com.example.Flexserver.domain.response.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, Exception.class})
    public ResponseEntity<Response> handleException(Exception e) {
        log.error("Exception : {} : {}", e.getMessage(), e);
        return ResponseEntity.ok(Response.builder()
                .status(Status.ERROR)
                .message("Exception occurred please contact system administrator # " + e.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {CommonDataAccessException.class})
    public ResponseEntity<Response> handleException(CommonDataAccessException e) {
        log.error("Exception : {} : {}", e.getMessage(), e);
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("data", e.getData()))
                .status(Status.ERROR)
                .message(e.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {CommonRuntimeException.class})
    public ResponseEntity<Response> handleException(CommonRuntimeException e) {
        log.error("Exception : {} : {}", e.getMessage(), e);
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("data", e.getData()))
                .status(Status.ERROR)
                .message(e.getMessage())
                .build());
    }
}
