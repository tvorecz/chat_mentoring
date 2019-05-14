package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageUpdateRequestDto;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.handler.Handler;

import java.time.LocalDateTime;

public class FillExistingMessageHandler implements Handler<MessageUpdateRequestDto, MessageResponseDto> {
    private MessageRepository repository;
    private Handler<Message, MessageResponseDto> nextHandler;

    public FillExistingMessageHandler(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageResponseDto handle(MessageUpdateRequestDto req, ServiceStatusResponseDto status) {
        if (req != null && status.getCode() == 200) {
            Message notUpdatedMessage = repository.findById(req.getMessageId())
                    .get();
            notUpdatedMessage.setText(req.getText());
            notUpdatedMessage.setDateOfUpdating(LocalDateTime.now().withNano(0));

            return nextHandler.handle(notUpdatedMessage, status);
        }

        return MessageResponseDto.builder()
                .status(status)
                .build();
    }
}
