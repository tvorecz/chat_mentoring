package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.dto.MessageDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.List;

public class ChatInfoResponseDtoMapperHandler implements Handler<Chat, ChatInfoResponseDto> {
    private Handler nextHandler;
    private Handler<List<User>, List<UserResponseDto>> listUserResponseDtoHandler;
    private Handler<List<Message>, List<MessageDto>> listMessageResponseDtoHandler;

    public ChatInfoResponseDtoMapperHandler(Handler<List<User>, List<UserResponseDto>> listUserResponseDtoHandler,
                                            Handler<List<Message>, List<MessageDto>> listMessageResponseDtoHandler) {
        this.listUserResponseDtoHandler = listUserResponseDtoHandler;
        this.listMessageResponseDtoHandler = listMessageResponseDtoHandler;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoResponseDto handle(Chat req, ServiceStatusResponseDto status) {
        if (req != null) {
            return ChatInfoResponseDto.builder()
                    .id(req.getId())
                    .title(req.getTitle())
                    .participants(listUserResponseDtoHandler.handle(req.getUsers(), status))
                    .lastHistory(listMessageResponseDtoHandler.handle(req.getMessages(), status))
                    .status(status)
                    .build();
        } else {
            return null;
        }
    }
}
