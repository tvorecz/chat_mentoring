package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.service.validator.annotation.MessageExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MessageExistsValidator implements ConstraintValidator<MessageExists, Integer> {
    private MessageRepository repository;

    @Autowired
    public MessageExistsValidator(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer message, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findById(message)
                .isPresent();
    }
}
