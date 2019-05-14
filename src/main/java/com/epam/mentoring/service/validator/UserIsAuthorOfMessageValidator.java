package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageRequestDto;
import com.epam.mentoring.service.validator.annotation.UserIsAuthorOfMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIsAuthorOfMessageValidator
        implements ConstraintValidator<UserIsAuthorOfMessage, MessageRequestDto> {
    private MessageRepository repository;

    @Autowired
    public UserIsAuthorOfMessageValidator(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(MessageRequestDto messageRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findByIdAndChat_IdAndUser_Id(messageRequestDto.getMessageId(),
                                                       messageRequestDto.getChatId(),
                                                       messageRequestDto.getUserId())
                .isPresent();
    }
}
