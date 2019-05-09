package com.epam.mentoring.service;

import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageRequestDto;
import com.epam.mentoring.dto.MessageResponseDto;

import java.util.List;

public interface MessageService {
    MessageResponseDto create(MessageRequestDto messageParams);

    MessageResponseDto update(MessageRequestDto messageParams);

    void delete(MessageRequestDto messageParams);

    List<MessageResponseDto> getMessages(MessageHistoryRequestDto messageHistory);
}
