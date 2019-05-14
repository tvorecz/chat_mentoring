package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dal.repository.RepositoryStorage;
import com.epam.mentoring.dto.ChatCreateRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChatCreateRequestDtoMapperHandler implements Handler<ChatCreateRequestDto, ChatInfoResponseDto> {
    private Handler<Chat, ChatInfoResponseDto> nextHandler;
    private RepositoryStorage repositoryStorage;

    public ChatCreateRequestDtoMapperHandler(RepositoryStorage repositoryStorage) {
        this.repositoryStorage = repositoryStorage;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoResponseDto handle(ChatCreateRequestDto req, ServiceStatusResponseDto status) {
        if (req != null && status.getCode() == 200) {
            Iterable<User> users = repositoryStorage.getUserRepository()
                    .findAllById(createListOfChatMembersIds(req));


            Chat notSavedChat = Chat.builder()
                    .title(req.getTitle())
                    .users(createListOfChatMembers(users))
                    .build();

            return nextHandler.handle(notSavedChat, status);
        }

        return ChatInfoResponseDto.builder()
                .status(status)
                .build();
    }

    private List<Integer> createListOfChatMembersIds(ChatCreateRequestDto req) {
        List<Integer> members = new ArrayList<>();

        members.add(req.getUserId());

        for (Integer participantsId : req.getParticipantsIds()) {
            members.add(participantsId);
        }

        return members;
    }

    private List<User> createListOfChatMembers(Iterable<User> users) {
        List<User> members = new ArrayList<>();

        for (User user : users) {
            members.add(user);
        }

        return members;
    }
}
