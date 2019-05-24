package com.epam.mentoring.service.mapper;

import com.epam.mentoring.dto.ChatInfoDto;
import com.epam.mentoring.dto.MessageDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.mapper.response.ChatInfoDtoMapper;
import com.epam.mentoring.service.mapper.response.ListResponseDtoMapper;
import com.epam.mentoring.service.mapper.response.MessageDtoMapper;
import com.epam.mentoring.service.mapper.response.UserResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperStorage {
    private ListResponseDtoMapper<User, UserResponseDto> userListResponseDtoMapper;
    private ListResponseDtoMapper<Chat, ChatInfoDto> chatListResponseDtoMapper;
    private ListResponseDtoMapper<Message, MessageDto> messageListMessageDtoMapper;
    private UserResponseDtoMapper userResponseDtoMapper;
    private ChatInfoDtoMapper chatInfoSimpleDtoMapper;
    private MessageDtoMapper messageDtoMapper;

    public MapperStorage() {
        userResponseDtoMapper = new UserResponseDtoMapper();

        messageDtoMapper = new MessageDtoMapper(userResponseDtoMapper);

        userListResponseDtoMapper = new ListResponseDtoMapper<>(userResponseDtoMapper);

        chatInfoSimpleDtoMapper = new ChatInfoDtoMapper(userListResponseDtoMapper);

        chatListResponseDtoMapper = new ListResponseDtoMapper<>(chatInfoSimpleDtoMapper);

        messageListMessageDtoMapper = new ListResponseDtoMapper<>(messageDtoMapper);
    }

    public UserResponseDtoMapper userToUserResponseDto() {
        return userResponseDtoMapper;
    }

    public ListResponseDtoMapper<User, UserResponseDto> userListToUserResponseDtoList() {
        return userListResponseDtoMapper;
    }

    public ListResponseDtoMapper<Chat, ChatInfoDto> chatListToChatsResponseDtoList() {
        return chatListResponseDtoMapper;
    }

    public ChatInfoDtoMapper chatToChatInfoDto() {
        return chatInfoSimpleDtoMapper;
    }

    public ListResponseDtoMapper<Message, MessageDto> messageListToMessageDtoList() {
        return messageListMessageDtoMapper;
    }

    public MessageDtoMapper messageToMessageDto() {
        return messageDtoMapper;
    }
}
