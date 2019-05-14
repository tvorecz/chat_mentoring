package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.RepositoryStorage;
import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.MessageService;
import com.epam.mentoring.service.handler.Handler;
import com.epam.mentoring.service.handler.chainbuilder.HandlerChainBuilder;
import com.epam.mentoring.service.handler.impl.logic.CreateMessageHandler;
import com.epam.mentoring.service.handler.impl.logic.DeleteMessageHandler;
import com.epam.mentoring.service.handler.impl.logic.GetChatHistoryHandler;
import com.epam.mentoring.service.handler.impl.logic.UpdateMessageHandler;
import com.epam.mentoring.service.handler.impl.mapper.request.FillExistingMessageHandler;
import com.epam.mentoring.service.handler.impl.mapper.request.FillMessageForSavingHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.ListResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.MessageDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.UserResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.validator.ValidatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class MessageServiceImpl implements MessageService {
    private RepositoryStorage repositoryStorage;

    private Handler<MessageHistoryRequestDto, MessageHistoryResponseDto> getChatHistoryHandler;
    private Handler<MessageCreateRequestDto, MessageResponseDto> createMessageHandler;
    private Handler<MessageUpdateRequestDto, MessageResponseDto> updateMessageHandler;
    private Handler<MessageDeleteRequestDto, ServiceStatusResponseDto> deleteMessageHandler;

    @Autowired
    public MessageServiceImpl(Validator validator, RepositoryStorage repositoryStorage) {
        this.repositoryStorage = repositoryStorage;

        getChatHistoryHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new GetChatHistoryHandler(repositoryStorage.getMessageRepository()))
                .nextHandler(new ListResponseDtoMapperHandler())
                .nextHandler(new MessageDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        createMessageHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new FillMessageForSavingHandler(repositoryStorage))
                .nextHandler(new CreateMessageHandler(repositoryStorage.getMessageRepository()))
                .nextHandler(new MessageDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        updateMessageHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new FillExistingMessageHandler(repositoryStorage.getMessageRepository()))
                .nextHandler(new UpdateMessageHandler(repositoryStorage.getMessageRepository()))
                .nextHandler(new MessageDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        deleteMessageHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new DeleteMessageHandler(repositoryStorage.getMessageRepository()))
                .build();
    }

    @Override
    public MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto) {
        return getChatHistoryHandler.handle(messageHistoryRequestDto,
                                            ServiceStatusResponseDto.builder()
                                                    .build());
    }

    @Override
    public MessageResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        return createMessageHandler.handle(messageCreateRequestDto,
                                           ServiceStatusResponseDto.builder()
                                                   .build());
    }

    @Override
    public MessageResponseDto updateMessage(MessageUpdateRequestDto messageUpdateRequestDto) {
        return updateMessageHandler.handle(messageUpdateRequestDto,
                                           ServiceStatusResponseDto.builder()
                                                   .build());
    }

    @Override
    public ServiceStatusResponseDto deleteMessage(MessageDeleteRequestDto messageDeleteRequestDto) {
        return deleteMessageHandler.handle(messageDeleteRequestDto,
                                           ServiceStatusResponseDto.builder()
                                                   .build());
    }
}
