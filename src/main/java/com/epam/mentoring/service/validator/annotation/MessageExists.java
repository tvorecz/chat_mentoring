package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.MessageExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = MessageExistsValidator.class)
@Documented
public @interface MessageExists {
    String message() default "Message doesn't exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
