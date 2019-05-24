package com.epam.mentoring.service.facade;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.ChatServiceFacade;
import com.epam.mentoring.service.ServiceStorage;
import com.epam.mentoring.service.mapper.MapperStorage;
import com.epam.mentoring.service.status.StatusResponse;
import com.epam.mentoring.service.status.StatusResponseFiller;
import com.epam.mentoring.service.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatServiceFacadeImpl implements ChatServiceFacade {
    private ServiceStorage serviceStorage;
    private StatusResponseFiller statusResponseFiller;
    private CommonValidator commonValidator;
    private MapperStorage mapperStorage;

    @Autowired
    public ChatServiceFacadeImpl(ServiceStorage serviceStorage,
                                 StatusResponseFiller statusResponseFiller,
                                 CommonValidator commonValidator,
                                 MapperStorage mapperStorage) {
        this.serviceStorage = serviceStorage;
        this.statusResponseFiller = statusResponseFiller;
        this.commonValidator = commonValidator;
        this.mapperStorage = mapperStorage;
    }

    @Override
    public ChatsResponseDto findAllChatsForUser(ChatsRequestDto chatsRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(chatsRequestDto);

        List<Chat> chats = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            chats = serviceStorage.getChatService()
                    .findAllChatsForUser(chatsRequestDto.getUserId());

            if (chats == null || chats.size() == 0) {
                statusResponseFiller.setEmptyResponseStatus(statusResponseDto);
                chats = null;
            }
        }

        return ChatsResponseDto.builder()
                .status(statusResponseDto)
                .chats(mapperStorage.chatListToChatsResponseDtoList()
                               .handle(chats))
                .build();
    }

    @Override
    public ChatInfoResponseDto createNewChat(ChatCreateRequestDto chatCreateRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(chatCreateRequestDto);

        Chat savedChat = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            List<User> users = serviceStorage.getUserService()
                    .findUsers(createListOfChatMembersIds(chatCreateRequestDto));

            Chat notSavedChat = Chat.builder()
                    .title(chatCreateRequestDto.getTitle())
                    .users(users)
                    .build();

            savedChat = serviceStorage.getChatService()
                    .createNewChat(notSavedChat);

        }

        return ChatInfoResponseDto.builder()
                .status(statusResponseDto)
                .chat(mapperStorage.chatToChatInfoDto()
                              .handle(savedChat))
                .lastHistory(null)
                .build();
    }

    @Override
    public ChatInfoResponseDto getChatInfo(ChatInfoRequestDto chatInfoRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(chatInfoRequestDto);

        Chat chat = null;

        List<Message> lastHistory = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            chat = serviceStorage.getChatService()
                    .getChatInfo(chatInfoRequestDto.getChatId());

            if (chat != null) {
                LocalDateTime yesterday = LocalDateTime.now()
                        .minusDays(1);

                lastHistory = serviceStorage.getMessageService()
                        .getChatHistory(chatInfoRequestDto.getChatId(),
                                        LocalDateTime.of(yesterday.getYear(),
                                                         yesterday.getMonth(),
                                                         yesterday.getDayOfMonth(),
                                                         0,
                                                         0,
                                                         0));

                if(lastHistory == null || lastHistory.size() == 0) {
                    lastHistory = null;
                }
            } else {
                statusResponseFiller.setEmptyResponseStatus(statusResponseDto);
            }
        }

        return ChatInfoResponseDto.builder()
                .status(statusResponseDto)
                .chat(mapperStorage.chatToChatInfoDto()
                              .handle(chat))
                .lastHistory(mapperStorage.messageListToMessageDtoList()
                                     .handle(lastHistory))
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
}
