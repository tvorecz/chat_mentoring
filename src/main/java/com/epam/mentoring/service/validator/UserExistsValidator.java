package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.validator.annotation.UserExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserExistsValidator implements ConstraintValidator<UserExists, Integer> {
    private UserRepository repository;

    @Autowired
    public UserExistsValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        if(id > 0) {
            Optional<User> user = repository.findById(id);

            return user.isPresent();
        }

        return false;
    }
}
