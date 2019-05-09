package com.epam.mentoring.service;

import com.epam.mentoring.dto.ChatRequestDto;
import com.epam.mentoring.dto.ChatResponseDto;

import java.util.List;

public interface ChatService {
    List<ChatResponseDto> getChats(ChatRequestDto chatParams);

    ChatResponseDto create(ChatRequestDto chatParams);

    ChatResponseDto getChat(ChatRequestDto chatParams);
}
