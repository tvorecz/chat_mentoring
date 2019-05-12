package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.LoginNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = LoginNotExistsValidator.class)
@Documented
public @interface LoginNotExists {
    String message() default "Login is already used.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
