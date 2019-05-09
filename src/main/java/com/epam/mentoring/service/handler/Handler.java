package com.epam.mentoring.service.handler;

import com.epam.mentoring.dto.ServiceStatusResponseDto;

public interface Handler<RQDTO, RSDTO> {
    RSDTO handle(RQDTO req, ServiceStatusResponseDto status);
}
