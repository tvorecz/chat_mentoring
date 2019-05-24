package com.epam.mentoring.service.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getChatHistory(int chatId, LocalDateTime from) {
        return messageRepository.getChatHistoryFromDate(chatId, from);
    }

    @Override
    public List<Message> getChatHistory(int chatId, LocalDateTime from, LocalDateTime till) {
        return messageRepository.getChatHistoryBetweenDates(chatId, from, till);
    }

    @Override
    public Message getById(int messageId) {
        Optional<Message> message = messageRepository.findById(messageId);

        return message.isPresent() ? message.get() : null;
    }

    @Override
    public Message createMessage(Message notSavedMessage) {
        notSavedMessage.setDateOfCreation(LocalDateTime.now().withNano(0));

        return messageRepository.save(notSavedMessage);
    }

    @Override
    public Message updateMessage(Message notUpdatedMessage) {
        notUpdatedMessage.setDateOfUpdating(LocalDateTime.now().withNano(0));

        return messageRepository.save(notUpdatedMessage);
    }

    @Override
    public void deleteMessage(int messageId) {
        messageRepository.deleteById(messageId);
    }
}
