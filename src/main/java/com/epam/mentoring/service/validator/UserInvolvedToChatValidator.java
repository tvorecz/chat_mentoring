package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.UserChatRepository;
import com.epam.mentoring.dto.UserInvolvedToChatDto;
import com.epam.mentoring.entity.UserChat;
import com.epam.mentoring.service.validator.annotation.UserInvolvedToChat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserInvolvedToChatValidator implements ConstraintValidator<UserInvolvedToChat, UserInvolvedToChatDto> {
    private UserChatRepository repository;

    @Autowired
    public UserInvolvedToChatValidator(UserChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(UserInvolvedToChatDto userInvolvedToChatDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(userInvolvedToChatDto.getChatId() > 0 && userInvolvedToChatDto.getUserId() > 0){
            Optional<UserChat> userChat = repository.findByUserAndChatId(userInvolvedToChatDto.getUserId(),
                                                                                userInvolvedToChatDto.getChatId());
            return userChat.isPresent();
        }

        return false;
    }
}
