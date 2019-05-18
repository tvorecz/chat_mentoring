package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageDeleteRequestDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.StatusResponseDto;
import com.epam.mentoring.service.handler.Handler;

public class DeleteMessageHandler implements Handler<MessageDeleteRequestDto, StatusResponseDto> {
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
    public StatusResponseDto handle(MessageDeleteRequestDto req, ServiceStatusResponseDto status) {
        if (req != null && status.getCode() == 200) {
            repository.deleteById(req.getMessageId());
        }

        return StatusResponseDto.builder()
                .status(status)
                .build();
    }
}
