package com.epam.mentoring.service;

import com.epam.mentoring.dto.*;

public interface ChatServiceFacade {
    ChatsResponseDto findAllChatsForUser(ChatsRequestDto chatsRequestDto);

    ChatInfoResponseDto createNewChat(ChatCreateRequestDto chatCreateRequestDto);

    ChatInfoResponseDto getChatInfo(ChatInfoRequestDto chatInfoRequestDto);

}
