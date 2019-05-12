package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.UsersExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsersExistValidator.class)
@Documented
public @interface UsersExist {
    String message() default "Some of users doesn't exist.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
