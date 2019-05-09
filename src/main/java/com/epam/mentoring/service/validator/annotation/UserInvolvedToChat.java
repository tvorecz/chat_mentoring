package com.epam.mentoring.service.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = )
@Documented
public @interface UserInvolvedToChat {
    String message() default "User is not participant of the chat";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
