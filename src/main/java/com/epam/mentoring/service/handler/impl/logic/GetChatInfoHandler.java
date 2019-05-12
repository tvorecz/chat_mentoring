package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.ChatRepository;
import com.epam.mentoring.dto.ChatInfoRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.handler.Handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetChatInfoHandler implements Handler<ChatInfoRequestDto, ChatInfoResponseDto> {
    private ChatRepository repository;
    private Handler<Chat, ChatInfoResponseDto> nextHandler;

    public GetChatInfoHandler(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public ChatInfoResponseDto handle(ChatInfoRequestDto req, ServiceStatusResponseDto status) {
        if (status.getCode() == 200) {
            LocalDateTime yesterday = LocalDateTime.now()
                    .minusDays(1);

            Optional<List<Object[]>> chatInfoWithLastHistory = repository.findChatInfoByChatIdWithLastMessages(req.getChatId(),
                                                                                         LocalDateTime.of(yesterday.getYear(),
                                                                                                       yesterday.getMonth(),
                                                                                                       yesterday.getDayOfMonth(),
                                                                                                       0,
                                                                                                       0,
                                                                                                       0));

            if (chatInfoWithLastHistory.isPresent()) {
                Chat currentChat = parseChatInfoWithLastHistory(chatInfoWithLastHistory.get());

                return nextHandler.handle(currentChat, status);
            }

            status.setCode(204);
            status.setMessage("No content.");
        }

        return ChatInfoResponseDto.builder()
                .status(status)
                .build();
    }

    private Chat parseChatInfoWithLastHistory(List<Object[]> chatAndMessages) {
        Chat currentChat = (Chat) chatAndMessages.get(0)[0];

        List<Message> lastHistory = new ArrayList<>();

        for (Object[] chatAndMessage : chatAndMessages) {
            lastHistory.add((Message) chatAndMessage[1]);
        }

        currentChat.setMessages(lastHistory);

        return currentChat;
    }
}
