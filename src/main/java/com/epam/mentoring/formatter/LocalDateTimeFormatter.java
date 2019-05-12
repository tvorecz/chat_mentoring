package com.epam.mentoring.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.toString();
    }
}
