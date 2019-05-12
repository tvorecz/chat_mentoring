package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;

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
        User savedUser = userRepository.save(notSavedUser);

        return nextHandler.handle(savedUser, status);
    }
}
