package com.epam.mentoring.exceptionhandler;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.StatusResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StatusResponseDto> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.status(400)
                .body(StatusResponseDto.builder()
                              .status(ServiceStatusResponseDto.builder()
                                              .message(ex.getMessage())
                                              .code(400)
                                              .build())
                              .build());
    }


}
