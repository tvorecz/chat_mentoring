package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.RepositoryStorage;
import com.epam.mentoring.dto.*;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.service.handler.Handler;
import com.epam.mentoring.service.handler.chainbuilder.HandlerChainBuilder;
import com.epam.mentoring.service.handler.impl.logic.UserRegisterHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.ListResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.logic.SearchUserHandler;
import com.epam.mentoring.service.handler.impl.mapper.request.UserRegisterRequestDtoMapper;
import com.epam.mentoring.service.handler.impl.mapper.response.UserRegisterResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.mapper.response.UserResponseDtoMapperHandler;
import com.epam.mentoring.service.handler.impl.validator.ValidatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private RepositoryStorage repositoryStorage;

    private Handler<UsersSearchRequestDto, UsersSearchResponseDto> findUsersHandler;
    private Handler<UserRegisterRequestDto, UserRegisterResponseDto> registerHandler;

    @Autowired
    public UserServiceImpl(Validator validator, RepositoryStorage repositoryStorage, PasswordEncoder passwordEncoder) {
        this.repositoryStorage = repositoryStorage;

        findUsersHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new SearchUserHandler(repositoryStorage.getUserRepository()))
                .nextHandler(new ListResponseDtoMapperHandler<User, UserResponseDto>())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        registerHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(validator))
                .nextHandler(new UserRegisterRequestDtoMapper(passwordEncoder))
                .nextHandler(new UserRegisterHandler(repositoryStorage.getUserRepository()))
                .nextHandler(new UserRegisterResponseDtoMapperHandler())
                .build();
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        return registerHandler.handle(userRegisterRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public UsersSearchResponseDto findUsers(UsersSearchRequestDto usersSearchRequestDto) {
        return findUsersHandler.handle(usersSearchRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = repositoryStorage.getUserRepository().findByLogin(login);

        return user.orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
    }
}
