package com.epam.mentoring.service.validator.handler;

import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.service.validator.annotation.CorrectPairOfDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectPairOfDatesValidator implements ConstraintValidator<CorrectPairOfDates, MessageHistoryRequestDto> {

    @Override
    public boolean isValid(MessageHistoryRequestDto messageHistoryRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return isFromDateExists(messageHistoryRequestDto) && isFromDateLessTillDate(messageHistoryRequestDto);
    }

    private boolean isFromDateLessTillDate(MessageHistoryRequestDto messageHistoryRequestDto) {
        if(isTillDateExists(messageHistoryRequestDto)) {
            return messageHistoryRequestDto.getFrom().isBefore(messageHistoryRequestDto.getTill());
        }

        return true;
    }

    private boolean isFromDateExists(MessageHistoryRequestDto messageHistoryRequestDto) {
        return messageHistoryRequestDto.getFrom() != null;
    }

    private boolean isTillDateExists(MessageHistoryRequestDto messageHistoryRequestDto) {
        return messageHistoryRequestDto.getTill() != null;
    }
}
