package com.epam.mentoring.service;

import com.epam.mentoring.dto.*;

public interface MessageServiceFacade {
    MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto);

    MessageResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto);

    MessageResponseDto updateMessage(MessageUpdateRequestDto messageUpdateRequestDto);

    StatusResponseDto deleteMessage(MessageDeleteRequestDto messageDeleteRequestDto);
}
