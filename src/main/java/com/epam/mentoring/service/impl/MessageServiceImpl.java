package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageHistoryResponseDto;
import com.epam.mentoring.service.MessageService;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.validation.Validator;

public class MessageServiceImpl implements MessageService {
    private Validator validator;
    private MessageRepository repository;

    private Handler<MessageHistoryRequestDto, MessageHistoryResponseDto> getMessagesHandler;

    @Autowired
    public MessageServiceImpl(Validator validator, MessageRepository repository) {
        this.validator = validator;
        this.repository = repository;


    }

    @Override
    public MessageHistoryResponseDto getMessages(MessageHistoryRequestDto messageHistoryRequestDto) {
        return null;
    }
}
