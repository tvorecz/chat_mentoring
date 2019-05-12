package com.epam.mentoring.service.handler.impl.mapper.request;

import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegisterRequestDtoMapper implements Handler<UserRegisterRequestDto, UserRegisterResponseDto> {
    private Handler<User, UserRegisterResponseDto> nextHandler;
    private PasswordEncoder passwordEncoder;

    public UserRegisterRequestDtoMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UserRegisterResponseDto handle(UserRegisterRequestDto req, ServiceStatusResponseDto status) {
        User user =  User.builder()
                .login(req.getLogin())
                .nickname(req.getNickname())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        return nextHandler.handle(user, status);
    }
}
