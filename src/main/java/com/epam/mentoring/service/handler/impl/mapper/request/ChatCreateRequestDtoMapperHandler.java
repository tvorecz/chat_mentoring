package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dto.ChatCreateRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.ArrayList;
import java.util.List;

public class ChatCreateRequestDtoMapperHandler implements Handler<ChatCreateRequestDto, ChatInfoResponseDto> {
    private Handler<Chat, ChatInfoResponseDto> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoResponseDto handle(ChatCreateRequestDto req, ServiceStatusResponseDto status) {
        if (req != null && status.getCode() == 200) {
            List<User> partisipants = new ArrayList<>();

            for (Integer userId : req.getParticipantsIds()) {
                partisipants.add(User.builder()
                                         .id(userId)
                                         .build());
            }

            Chat notSavedChat = Chat.builder()
                    .title(req.getTitle())
                    .users(partisipants)
                    .build();

            return nextHandler.handle(notSavedChat, status);
        }

        return ChatInfoResponseDto.builder()
                .status(status)
                .build();
    }
}
