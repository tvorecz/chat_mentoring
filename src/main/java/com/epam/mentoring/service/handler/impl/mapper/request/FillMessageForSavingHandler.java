package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dal.repository.RepositoryStorage;
import com.epam.mentoring.dto.MessageCreateRequestDto;
import com.epam.mentoring.dto.MessageResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.handler.Handler;

import java.time.LocalDateTime;

public class FillMessageForSavingHandler implements Handler<MessageCreateRequestDto, MessageResponseDto> {
    private RepositoryStorage repositoryStorage;
    private Handler<Message, MessageResponseDto> nextHandler;

    public FillMessageForSavingHandler(RepositoryStorage repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public MessageResponseDto handle(MessageCreateRequestDto req, ServiceStatusResponseDto status) {
        if (req != null && status.getCode() == 200) {

            Message notSavedMessage = Message.builder()
                    .text(req.getText())
                    .user(repositoryStorage.getUserRepository().findById(req.getUserId()).get())
                    .chat(repositoryStorage.getChatRepository().findById(req.getChatId()).get())
                    .dateOfCreation(LocalDateTime.now().withNano(0))
                    .build();

            return nextHandler.handle(notSavedMessage, status);

        }

        return MessageResponseDto.builder()
                .status(status)
                .build();
    }
}
