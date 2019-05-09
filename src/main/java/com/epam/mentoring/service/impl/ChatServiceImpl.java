package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.dto.ChatsRequestDto;
import com.epam.mentoring.dto.ChatsResponseDto;
import com.epam.mentoring.service.ChatService;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public class ChatServiceImpl implements ChatService {
    private Validator validator;
    private ChatRepository repository;

    private Handler<ChatsRequestDto, ChatsResponseDto> findAllChatsForUserHandler;

    @Autowired
    public ChatServiceImpl(Validator validator, ChatRepository repository) {
        this.validator = validator;
        this.repository = repository;


    }

    @Override
    public ChatsResponseDto findAllChatsForUser(ChatsRequestDto chatsRequestDto) {

        return null;
    }
}
