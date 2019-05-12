package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

import java.util.Collections;

public class UserRegisterHandler implements Handler<User, UserRegisterResponseDto> {
    private UserRepository userRepository;
    private Handler<User, UserRegisterResponseDto> nextHandler;

    public UserRegisterHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UserRegisterResponseDto handle(User notSavedUser, ServiceStatusResponseDto status) {
        if (status.getCode() == 200) {
            User savedUser = userRepository.save(notSavedUser);

            return nextHandler.handle(savedUser, status);
        }

        return UserRegisterResponseDto.builder()
                .status(status)
                .build();
    }
}
