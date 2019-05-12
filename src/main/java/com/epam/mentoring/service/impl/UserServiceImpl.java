package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.UserRepository;
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
    private Validator validator;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private Handler<UserSearchRequestDto, UserSearchResponseDto> findUsersHandler;
    private Handler<UserRegisterRequestDto, UserRegisterResponseDto> registerHandler;

    @Autowired
    public UserServiceImpl(Validator validator, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        findUsersHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(this.validator))
                .nextHandler(new SearchUserHandler(this.userRepository))
                .nextHandler(new ListResponseDtoMapperHandler<User, UserResponseDto>())
                .nextHandler(new UserResponseDtoMapperHandler())
                .build();

        registerHandler = HandlerChainBuilder.builder()
                .startHandler(new ValidatorHandler(this.validator))
                .nextHandler(new UserRegisterRequestDtoMapper(passwordEncoder))
                .nextHandler(new UserRegisterHandler(this.userRepository))
                .nextHandler(new UserRegisterResponseDtoMapperHandler())
                .build();
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        return registerHandler.handle(userRegisterRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public UserSearchResponseDto findUsers(UserSearchRequestDto userSearchRequestDto) {
        return findUsersHandler.handle(userSearchRequestDto, new ServiceStatusResponseDto());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);

        return user.orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
    }
}
