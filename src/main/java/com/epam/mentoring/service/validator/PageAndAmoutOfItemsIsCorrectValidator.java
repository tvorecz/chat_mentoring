package com.epam.mentoring.service.validator;

import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.service.validator.annotation.PageAndAmoutOfItemsIsCorrect;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PageAndAmoutOfItemsIsCorrectValidator
        implements ConstraintValidator<PageAndAmoutOfItemsIsCorrect, UsersSearchRequestDto> {


    @Override
    public boolean isValid(UsersSearchRequestDto usersSearchRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(isPageAndAmountNull(usersSearchRequestDto)) {
            return true;
        } else if(isPageAndAmountMoreThanZero(usersSearchRequestDto)) {
            return true;
        }

        return false;
    }

    private boolean isPageAndAmountNull(UsersSearchRequestDto usersSearchRequestDto) {
        return usersSearchRequestDto.getPage() == null && usersSearchRequestDto.getAmount() == null;
    }

    private boolean isPageAndAmountMoreThanZero(UsersSearchRequestDto usersSearchRequestDto) {
        return usersSearchRequestDto.getPage() > 0 && usersSearchRequestDto.getAmount() > 0;
    }
}
