package com.epam.mentoring.service.handler.impl.mapper.response;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.HashMap;
import java.util.Map;

public class UserRegisterResponseDtoMapperHandler implements Handler<User, UserRegisterResponseDto> {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UserRegisterResponseDto handle(User req, ServiceStatusResponseDto status) {
        if(req != null) {
            Map<String, String> links = new HashMap<>();
            links.put("login", "/secure/login");

            return UserRegisterResponseDto.builder()
                    .status(status)
                    .links(links)
                    .build();
        } else {
            return null;
        }

    }
}
