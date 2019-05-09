package com.epam.mentoring.service.handler.impl;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapperHandler implements Handler<User, UserResponseDto> {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UserResponseDto handle(User entity, ServiceStatusResponseDto status) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .nickname(entity.getNickname())
                .build();
    }
}
