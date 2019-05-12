package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.dto.ChatCreateRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.service.handler.Handler;

import java.util.Collections;

public class CreateNewChatHandler implements Handler<Chat, ChatInfoResponseDto> {
    private ChatRepository repository;
    private Handler<Chat, ChatInfoResponseDto> nextHandler;

    public CreateNewChatHandler(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoResponseDto handle(Chat notSavedChat, ServiceStatusResponseDto status) {
        if (status.getCode() == 200) {
            Chat savedChat = repository.save(notSavedChat);

            return nextHandler.handle(savedChat, status);
        }

        return ChatInfoResponseDto.builder()
                .status(status)
                .build();
    }
}
