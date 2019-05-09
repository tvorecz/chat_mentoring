package com.epam.mentoring.service.handler.impl;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.ArrayList;
import java.util.List;


public class ListUserResponseDtoMapperHandler implements Handler<List<User>, List<UserResponseDto>> {
    private Handler<User, UserResponseDto> nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public List<UserResponseDto> handle(List<User> entityList, ServiceStatusResponseDto status) {
        List<UserResponseDto> responseList = new ArrayList<>(entityList.size());

        for (User user : entityList) {
            responseList.add(nextHandler.handle(user, status));
        }

        return responseList;
    }
}
