package com.epam.mentoring.service.status;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class StatusResponseFiller {

    public ServiceStatusResponseDto createDefaultResponse() {
        return ServiceStatusResponseDto.builder().build();
    }

    public void setSuccessfulStatus (ServiceStatusResponseDto statusResponseDto) {
        statusResponseDto.setCode(StatusResponse.SUCCESS.getCode());
        statusResponseDto.setMessage("Success.");
    }

    public <DTO> void setValidationProblemStatus(ServiceStatusResponseDto statusResponseDto, Set<ConstraintViolation<DTO>> violations) {
        statusResponseDto.setCode(StatusResponse.BAD_REQUEST.getCode());
        statusResponseDto.setMessage(createMessage(violations));
    }

    public void setEmptyResponseStatus(ServiceStatusResponseDto statusResponseDto) {
        statusResponseDto.setCode(StatusResponse.NOT_FOUND.getCode());
        statusResponseDto.setMessage("Result is empty");
    }

    private <DTO> String createMessage(Set<ConstraintViolation<DTO>> violations) {
        StringBuilder buffer = new StringBuilder();

        for (ConstraintViolation<DTO> violation : violations) {
            buffer.append(violation.getMessage());
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
