package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.UserIsAuthorOfMessageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserIsAuthorOfMessageValidator.class)
@Documented
public @interface UserIsAuthorOfMessage {
    String message() default "User isn't author of message.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
