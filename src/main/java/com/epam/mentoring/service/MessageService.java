package com.epam.mentoring.service;

import com.epam.mentoring.dto.temp.MessageHistoryRequestDto;
import com.epam.mentoring.dto.temp.MessageHistoryResponseDto;

public interface MessageService {
    MessageHistoryResponseDto getMessages(MessageHistoryRequestDto messageHistoryRequestDto);
}
