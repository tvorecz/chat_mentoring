package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.UserChatRepository;
import com.epam.mentoring.dto.temp.MessageHistoryRequestDto;
import com.epam.mentoring.service.validator.annotation.UserInvolvedToChat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInvolvedToChatValidator implements ConstraintValidator<UserInvolvedToChat, MessageHistoryRequestDto> {
    private UserChatRepository repository;

    @Autowired
    public UserInvolvedToChatValidator(UserChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(MessageHistoryRequestDto messageHistoryRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
