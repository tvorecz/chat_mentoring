package com.epam.mentoring.service.validator;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.service.validator.annotation.UsersExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsersExistValidator implements ConstraintValidator<UsersExist, Integer[]> {
    private UserExistsValidator userExistsValidator;

    @Autowired
    public UsersExistValidator(UserRepository repository) {
        userExistsValidator = new UserExistsValidator(repository);
    }

    @Override
    public boolean isValid(Integer[] usersIds, ConstraintValidatorContext constraintValidatorContext) {
        for (Integer userId : usersIds) {
            if(!userExistsValidator.isValid(userId, constraintValidatorContext)){
                return false;
            }
        }

        return true;
    }
}
