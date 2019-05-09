package com.epam.mentoring.service.handler.impl;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.service.handler.Handler;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorHandler<RQDTO, RSDTO> implements Handler<RQDTO, RSDTO> {
    private Validator validator;
    private Handler<RQDTO, RSDTO> nextHandler;

    public ValidatorHandler(Validator validator) {
        this.validator = validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public RSDTO handle(RQDTO req, ServiceStatusResponseDto status) {
        Set<ConstraintViolation<RQDTO>> violations = validator.validate(req);

        if (violations.isEmpty()) {
            status.setCode(200);
            status.setMessage("Ok.");
        } else {
            status.setCode(400);
            status.setMessage(createMessage(violations));
        }

        return nextHandler.handle(req, status);
    }

    private String createMessage(Set<ConstraintViolation<RQDTO>> violations) {
        StringBuffer buffer = new StringBuffer();

        for (ConstraintViolation<RQDTO> violation : violations) {
            buffer.append(violation.getMessage());
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
