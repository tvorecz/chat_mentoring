package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.service.handler.Handler;

public class UpdateMessageHandler implements Handler<Message, MessageResponseDto> {
    private MessageRepository repository;
    private Handler<Message, MessageDto> nextHandler;

    public UpdateMessageHandler(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageResponseDto handle(Message notUpdatedMessage, ServiceStatusResponseDto status) {
        if (status.getCode() == 200 && notUpdatedMessage != null) {
            Message updatedMessage = repository.save(notUpdatedMessage);

            return MessageResponseDto.builder()
                    .message(nextHandler.handle(updatedMessage, status))
                    .status(status)
                    .build();
        }

        return MessageResponseDto.builder()
                .status(status)
                .build();
    }
}
