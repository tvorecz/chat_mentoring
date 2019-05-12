package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageHistoryResponseDto;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.handler.Handler;

import java.util.List;

public class GetChatHistoryHandler implements Handler<MessageHistoryRequestDto, MessageHistoryResponseDto> {
    private MessageRepository repository;
    private Handler<List<Message>, List<MessageResponseDto>> nextHandler;

    public GetChatHistoryHandler(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageHistoryResponseDto handle(MessageHistoryRequestDto req, ServiceStatusResponseDto status) {
        if (status.getCode() == 200) {
            List<Message> history;

            if (req.getTill() == null) {
                history = repository.getChatHistoryFromDate(req.getChatId(), req.getFrom());
            } else {
                history = repository.getChatHistoryBetweenDates(req.getChatId(), req.getFrom(), req.getTill());
            }

            return MessageHistoryResponseDto.builder()
                    .history(nextHandler.handle(history, status))
                    .status(status)
                    .build();
        }

        return MessageHistoryResponseDto.builder()
                .status(status)
                .build();
    }
}
