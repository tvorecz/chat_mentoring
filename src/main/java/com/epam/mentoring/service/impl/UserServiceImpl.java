package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.dto.UserSearchResponseDto;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.service.handler.Handler;
import com.epam.mentoring.service.handler.chainbuilder.HandlerChainBuilder;
import com.epam.mentoring.service.handler.impl.ListUserResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.SearchUserHandler;
import com.epam.mentoring.service.handler.impl.UserResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.ValidatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class UserServiceImpl implements UserService {
    private Validator validator;
    private UserRepository userRepository;

    private Handler<UserSearchRequestDto, UserSearchResponseDto> findUsersHandler;

    @Autowired
    public UserServiceImpl(Validator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;

        findUsersHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(this.validator))
                .nextHandler(new SearchUserHandler(this.userRepository))
                .nextHandler(new ListUserResponseDtoMapperHandler())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();
    }

    @Override
    public UserSearchResponseDto findUsers(UserSearchRequestDto userSearchRequestDto) {
        return findUsersHandler.handle(userSearchRequestDto, new ServiceStatusResponseDto());
    }


}
