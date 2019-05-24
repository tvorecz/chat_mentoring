package com.epam.mentoring.service.validator;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.service.status.StatusResponseFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class CommonValidator {
    private Validator validator;
    private StatusResponseFiller statusResponseFiller;

    @Autowired
    public CommonValidator(Validator validator,
                           StatusResponseFiller statusResponseFiller) {
        this.validator = validator;
        this.statusResponseFiller = statusResponseFiller;
    }

    public <DTO> ServiceStatusResponseDto validate(DTO objectForValidation) {
        ServiceStatusResponseDto statusResponseDto = statusResponseFiller.createDefaultResponse();

        Set<ConstraintViolation<DTO>> violations = validator.validate(objectForValidation);

        if (!violations.isEmpty()) {
            statusResponseFiller.setValidationProblemStatus(statusResponseDto, violations);
        }

        return statusResponseDto;
    }
}
