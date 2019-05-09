package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.UserExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UserExistsValidator.class)
@Documented
public @interface UserExists {
    String message() default "User doesn't exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
