package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.dto.ChatResponseDto;
import com.epam.mentoring.dto.ChatsRequestDto;
import com.epam.mentoring.dto.ChatsResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.service.handler.Handler;

import java.util.Collections;
import java.util.List;

public class AllChatsForUserHandler implements Handler<ChatsRequestDto, ChatsResponseDto> {
    private ChatRepository repository;
    private Handler<List<Chat>, List<ChatResponseDto>> nextHandler;

    public AllChatsForUserHandler(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatsResponseDto handle(ChatsRequestDto req, ServiceStatusResponseDto status) {
        List<Chat> chats;

        if(status.getCode() == 200) {
            chats = repository.findAllChatsForUser(req.getUserId());

            if(chats != null) {
                return new ChatsResponseDto(nextHandler.handle(chats, status), status);
            } else {
                status.setCode(204);
                status.setMessage("No content.");
            }
        }

        return new ChatsResponseDto(Collections.emptyList(), status);
    }
}
