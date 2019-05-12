package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.ChatInfoDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.List;

public class ChatInfoDtoMapperHandler implements Handler<Chat, ChatInfoDto> {
    private Handler<List<User>, List<UserResponseDto>> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoDto handle(Chat req, ServiceStatusResponseDto status) {
        if (req != null) {
            return ChatInfoDto.builder()
                    .id(req.getId())
                    .title(req.getTitle())
                    .participants(nextHandler.handle(req.getUsers(), status))
                    .build();
        } else {
            return null;
        }
    }
}
