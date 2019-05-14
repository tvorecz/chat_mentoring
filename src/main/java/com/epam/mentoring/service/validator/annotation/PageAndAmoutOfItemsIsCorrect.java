package com.epam.mentoring.service.validator.annotation;

import com.epam.mentoring.service.validator.PageAndAmoutOfItemsIsCorrectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PageAndAmoutOfItemsIsCorrectValidator.class)
@Documented
public @interface PageAndAmoutOfItemsIsCorrect {
    String message() default "Page and amount of items isn't correct.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
