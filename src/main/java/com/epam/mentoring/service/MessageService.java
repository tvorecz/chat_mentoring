package com.epam.mentoring.service;

import com.epam.mentoring.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {
    List<Message> getChatHistory(int chatId, LocalDateTime from);

    List<Message> getChatHistory(int chatId, LocalDateTime from, LocalDateTime till);

    Message getById(int messageId);

    Message createMessage(Message message);

    Message updateMessage(Message message);

    void deleteMessage(int messageId);
}
