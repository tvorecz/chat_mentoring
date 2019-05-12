package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dto.MessageCreateRequestDto;
import com.epam.mentoring.dto.MessageCreateResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.time.LocalDateTime;

public class MessageCreateRequestDtoHandler implements Handler<MessageCreateRequestDto, MessageCreateResponseDto> {
    private Handler<Message, MessageCreateResponseDto> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageCreateResponseDto handle(MessageCreateRequestDto req, ServiceStatusResponseDto status) {
        if (req != null) {

            Message notSavedMessage = Message.builder()
                    .text(req.getText())
                    .user(User.builder()
                                  .id(req.getUserId())
                                  .build())
                    .chat(Chat.builder()
                                  .id(req.getChatId())
                                  .build())
                    .dateOfCreation(LocalDateTime.now())
                    .build();

            return nextHandler.handle(notSavedMessage, status);

        }

        return MessageCreateResponseDto.builder()
                .status(status)
                .build();
    }
}
