package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.service.validator.annotation.ChatExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ChatExistsValidator implements ConstraintValidator<ChatExists, Integer> {
    private ChatRepository repository;

    @Autowired
    public ChatExistsValidator(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        if(id > 0) {
            Optional<Chat> chat = repository.findById(id);

            return chat.isPresent();
        }

        return false;
    }
}
