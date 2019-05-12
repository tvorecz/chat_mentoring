package com.epam.mentoring.service;

import com.epam.mentoring.dto.MessageCreateRequestDto;
import com.epam.mentoring.dto.MessageCreateResponseDto;
import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageHistoryResponseDto;

public interface MessageService {
    MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto);

    MessageCreateResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto);
}
