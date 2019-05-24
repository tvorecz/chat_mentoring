package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.handler.CorrectPairOfDatesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CorrectPairOfDatesValidator.class)
@Documented
public @interface CorrectPairOfDates {
    String message() default "Dates not correct.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
