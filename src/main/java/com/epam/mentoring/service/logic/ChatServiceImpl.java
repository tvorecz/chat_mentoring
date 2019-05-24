package com.epam.mentoring.service.logic;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;

    @Autowired

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<Chat> findAllChatsForUser(int userId) {
        List<Chat> chats = chatRepository.findAllChatsForUser(userId);

        return chats;
    }

    @Override
    public Chat createNewChat(Chat notSavedChat) {
        return chatRepository.save(notSavedChat);
    }

    @Override
    public Chat getChatInfo(int chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);

        return chat.isPresent() ? chat.get() : null;
    }
}
