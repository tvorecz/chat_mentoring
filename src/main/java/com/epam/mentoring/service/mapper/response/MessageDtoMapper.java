package com.epam.mentoring.service.mapper.response;

import com.epam.mentoring.dto.MessageDto;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.mapper.DtoMapper;

public class MessageDtoMapper implements DtoMapper<Message, MessageDto> {
    private UserResponseDtoMapper userResponseSimpleDtoMapper;

    public MessageDtoMapper(UserResponseDtoMapper userResponseSimpleDtoMapper) {
        this.userResponseSimpleDtoMapper = userResponseSimpleDtoMapper;
    }

    @Override
    public MessageDto handle(Message req) {
        if (req != null) {
            return MessageDto.builder()
                    .id(req.getId())
                    .author(userResponseSimpleDtoMapper.handle(req.getUser()))
                    .chatId(req.getChat()
                                    .getId())
                    .text(req.getText())
                    .dateTimeOfCreating(req.getDateOfCreation())
                    .dateTimeOfEditing(req.getDateOfUpdating())
                    .build();
        }

        return MessageDto.builder()
                .build();
    }
}
