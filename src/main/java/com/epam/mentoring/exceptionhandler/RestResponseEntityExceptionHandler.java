package com.epam.mentoring.exceptionhandler;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ServiceStatusResponseDto> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ServiceStatusResponseDto.builder()
                                            .message(ex.getMessage())
                                            .code(400)
                                            .build(),
                                    HttpStatus.OK);
    }


}
