package com.epam.mentoring.service.mapper.response;

import com.epam.mentoring.dto.ChatInfoDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.Chat;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.mapper.DtoMapper;

public class ChatInfoDtoMapper implements DtoMapper<Chat, ChatInfoDto> {
    private ListResponseDtoMapper<User, UserResponseDto> userListResponseDtoMapper;

    public ChatInfoDtoMapper(ListResponseDtoMapper<User, UserResponseDto> userListResponseDtoMapper) {
        this.userListResponseDtoMapper = userListResponseDtoMapper;
    }

    @Override
    public ChatInfoDto handle(Chat req) {
        if (req != null) {
            return ChatInfoDto.builder()
                    .id(req.getId())
                    .title(req.getTitle())
                    .participants(userListResponseDtoMapper.handle(req.getUsers()))
                    .build();
        } else {
            return null;
        }
    }
}
