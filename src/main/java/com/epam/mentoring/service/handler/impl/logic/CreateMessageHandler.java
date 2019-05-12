package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageCreateRequestDto;
import com.epam.mentoring.dto.MessageCreateResponseDto;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.handler.Handler;

public class CreateMessageHandler implements Handler<Message, MessageCreateResponseDto> {
    private MessageRepository repository;
    private Handler<Message, MessageResponseDto> nextHandler;

    public CreateMessageHandler(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageCreateResponseDto handle(Message notSavedMessage, ServiceStatusResponseDto status) {
        if (status.getCode() == 200 && notSavedMessage != null) {
            Message savedMessage = repository.save(notSavedMessage);

            savedMessage = repository.getCreatedMessage(savedMessage.getId());

            return MessageCreateResponseDto.builder()
                    .message(nextHandler.handle(savedMessage, status))
                    .status(status)
                    .build();
        }

        return MessageCreateResponseDto.builder()
                .status(status)
                .build();
    }
}
