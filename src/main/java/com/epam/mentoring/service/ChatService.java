package com.epam.mentoring.service;

import com.epam.mentoring.entity.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findAllChatsForUser(int userId);

    Chat createNewChat(Chat chat);

    Chat getChatInfo(int chatId);
}
