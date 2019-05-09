package com.epam.mentoring.service;

import com.epam.mentoring.dto.ChatsRequestDto;
import com.epam.mentoring.dto.ChatsResponseDto;

public interface ChatService {
    ChatsResponseDto findAllChatsForUser(ChatsRequestDto chatsRequestDto);

}
