package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageDeleteRequestDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.service.handler.Handler;

public class DeleteMessageHandler implements Handler<MessageDeleteRequestDto, ServiceStatusResponseDto> {
    private MessageRepository repository;
    private Handler nextHandler;

    public DeleteMessageHandler(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ServiceStatusResponseDto handle(MessageDeleteRequestDto req, ServiceStatusResponseDto status) {
        if(req != null && status.getCode() == 200) {
            repository.deleteById(req.getMessageId());
        }

        return status;
    }
}
