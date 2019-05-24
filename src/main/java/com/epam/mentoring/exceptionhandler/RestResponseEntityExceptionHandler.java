package com.epam.mentoring.exceptionhandler;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.StatusResponseDto;
import com.epam.mentoring.service.status.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StatusResponseDto> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.status(StatusResponse.BAD_REQUEST.getHttpStatus())
                .body(StatusResponseDto.builder()
                              .status(ServiceStatusResponseDto.builder()
                                              .message(ex.getMessage())
                                              .code(StatusResponse.BAD_REQUEST.getCode())
                                              .build())
                              .build());
    }


}
