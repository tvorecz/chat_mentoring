package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.handler.ChatExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ChatExistsValidator.class)
@Documented
public @interface ChatExists {
    String message() default "Chat doesn't exist.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
