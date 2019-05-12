package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.ChatResponseDto;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.List;

public class ChatResponseDtoMapperHandler implements Handler<Chat, ChatResponseDto> {
    private Handler nextHandler;
    private Handler<List<User>, List<UserResponseDto>> listUserResponseDtoHandler;
    private Handler<List<Message>, List<MessageResponseDto>> listMessageResponseDtoHandler;

    public ChatResponseDtoMapperHandler(Handler<List<User>, List<UserResponseDto>> listUserResponseDtoHandler,
                                        Handler<List<Message>, List<MessageResponseDto>> listMessageResponseDtoHandler) {
        this.listUserResponseDtoHandler = listUserResponseDtoHandler;
        this.listMessageResponseDtoHandler = listMessageResponseDtoHandler;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatResponseDto handle(Chat req, ServiceStatusResponseDto status) {
        return ChatResponseDto.builder()
                .id(req.getId())
                .title(req.getTitle())
                .participants(listUserResponseDtoHandler.handle(req.getUsers(), status))
                .history(listMessageResponseDtoHandler.handle(req.getMessages(),status))
                .build();
    }
}
