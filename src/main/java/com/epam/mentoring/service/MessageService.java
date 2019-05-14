package com.epam.mentoring.service;

import com.epam.mentoring.dto.*;

public interface MessageService {
    MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto);

    MessageResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto);

    MessageResponseDto updateMessage(MessageUpdateRequestDto messageUpdateRequestDto);

    ServiceStatusResponseDto deleteMessage(MessageDeleteRequestDto messageDeleteRequestDto);
}
