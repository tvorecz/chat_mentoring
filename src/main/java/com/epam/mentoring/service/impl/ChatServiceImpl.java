package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.dto.*;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.ChatService;
import com.epam.mentoring.service.handler.Handler;
import com.epam.mentoring.service.handler.chainbuilder.HandlerChainBuilder;
import com.epam.mentoring.service.handler.impl.logic.AllChatsForUserHandler;
import com.epam.mentoring.service.handler.impl.logic.CreateNewChatHandler;
import com.epam.mentoring.service.handler.impl.logic.GetChatInfoHandler;
import com.epam.mentoring.service.handler.impl.mapper.request.ChatCreateRequestDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.*;
import com.epam.mentoring.service.handler.impl.validator.ValidatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {
    private Validator validator;
    private ChatRepository repository;

    private Handler<ChatsRequestDto, ChatsResponseDto> findAllChatsForUserHandler;
    private Handler<ChatInfoRequestDto, ChatInfoResponseDto> getChatInfoHandler;
    private Handler<ChatCreateRequestDto, ChatInfoResponseDto> createNewChatHandler;

    @Autowired
    public ChatServiceImpl(Validator validator, ChatRepository repository) {
        this.validator = validator;
        this.repository = repository;

        findAllChatsForUserHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new AllChatsForUserHandler(repository))
                .nextHandler(new ListResponseDtoMapperHandler<Chat, ChatInfoResponseDto>())
                .nextHandler(new ChatInfoDtoMapperHandler())
                .nextHandler(new ListResponseDtoMapperHandler<User, UserResponseDto>())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();


        getChatInfoHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new GetChatInfoHandler(repository))
                .nextHandler(new ChatInfoResponseDtoMapperHandler(
                        HandlerChainBuilder.builder()
                                .startHandler(new ListResponseDtoMapperHandler<User, UserResponseDto>())
                                .nextHandler(new UserResponseDtoMapperHandler())
                                .build(),
                        HandlerChainBuilder.builder()
                                .startHandler(new ListResponseDtoMapperHandler<Message, MessageResponseDto>())
                                .nextHandler(HandlerChainBuilder.builder()
                                                     .startHandler(new MessageResponseDtoMapperHandler())
                                                     .nextHandler(new UserResponseDtoMapperHandler())
                                                     .build())
                                .build()))
                .build();

        createNewChatHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new ChatCreateRequestDtoMapperHandler())
                .nextHandler(new CreateNewChatHandler(repository))
                .nextHandler(new ChatInfoResponseDtoMapperHandler(
                        HandlerChainBuilder.builder()
                                .startHandler(new ListResponseDtoMapperHandler<User, UserResponseDto>())
                                .nextHandler(new UserResponseDtoMapperHandler())
                                .build(),
                        HandlerChainBuilder.builder()
                                .startHandler(new ListResponseDtoMapperHandler<Message, MessageResponseDto>())
                                .nextHandler(HandlerChainBuilder.builder()
                                                     .startHandler(new MessageResponseDtoMapperHandler())
                                                     .nextHandler(new UserResponseDtoMapperHandler())
                                                     .build())
                                .build()))
                .build();
    }

    @Override
    public ChatsResponseDto findAllChatsForUser(ChatsRequestDto chatsRequestDto) {
        return findAllChatsForUserHandler.handle(chatsRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public ChatInfoResponseDto createNewChat(ChatCreateRequestDto chatCreateRequestDto) {
        return createNewChatHandler.handle(chatCreateRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public ChatInfoResponseDto getChatInfo(ChatInfoRequestDto chatInfoRequestDto) {
        return getChatInfoHandler.handle(chatInfoRequestDto, new ServiceStatusResponseDto());
    }
}
