package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.service.validator.annotation.LoginNotExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginNotExistsValidator implements ConstraintValidator<LoginNotExists, String> {
    private UserRepository userRepository;

    @Autowired
    public LoginNotExistsValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByLogin(login)
                .isEmpty();
    }
}
