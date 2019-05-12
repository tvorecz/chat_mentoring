package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.MessageRepository;
import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.MessageService;
import com.epam.mentoring.service.handler.Handler;
import com.epam.mentoring.service.handler.chainbuilder.HandlerChainBuilder;
import com.epam.mentoring.service.handler.impl.logic.CreateMessageHandler;
import com.epam.mentoring.service.handler.impl.logic.GetChatHistoryHandler;
import com.epam.mentoring.service.handler.impl.mapper.request.MessageCreateRequestDtoHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.ListResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.MessageResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.UserResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.validator.ValidatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class MessageServiceImpl implements MessageService {
    private Validator validator;
    private MessageRepository repository;

    private Handler<MessageHistoryRequestDto, MessageHistoryResponseDto> getChatHistoryHandler;
    private Handler<MessageCreateRequestDto, MessageCreateResponseDto> createMessageHandler;

    @Autowired
    public MessageServiceImpl(Validator validator, MessageRepository repository) {
        this.validator = validator;
        this.repository = repository;

        getChatHistoryHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new GetChatHistoryHandler(repository))
                .nextHandler(new ListResponseDtoMapperHandler())
                .nextHandler(new MessageResponseDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        createMessageHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new MessageCreateRequestDtoHandler())
                .nextHandler(new CreateMessageHandler(repository))
                .nextHandler(new MessageResponseDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();
    }

    @Override
    public MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto) {
        return getChatHistoryHandler.handle(messageHistoryRequestDto,
                                            ServiceStatusResponseDto.builder()
                                                    .build());
    }

    @Override
    public MessageCreateResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        return createMessageHandler.handle(messageCreateRequestDto,
                                           ServiceStatusResponseDto.builder()
                                                   .build());
    }
}
