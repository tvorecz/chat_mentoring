package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

public class MessageResponseDtoMapperHandler implements Handler<Message, MessageResponseDto> {
    private Handler<User, UserResponseDto> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageResponseDto handle(Message req, ServiceStatusResponseDto status) {
        if(req != null) {
            return MessageResponseDto.builder()
                    .id(req.getId())
                    .author(nextHandler.handle(req.getUser(), status))
                    .chatId(req.getChat()
                                    .getId())
                    .text(req.getText())
                    .dateTimeOfCreating(req.getDateOfCreation())
                    .dateTimeOfEditing(req.getDateOfUpdating())
                    .build();
        } else {
            return null;
        }

    }
}
