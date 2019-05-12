package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.dto.UserSearchResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

public class SearchUserHandler implements Handler<UserSearchRequestDto, UserSearchResponseDto> {
    private UserRepository userRepository;
    private Handler<List<User>, List<UserResponseDto>> nextHandler;

    public SearchUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UserSearchResponseDto handle(UserSearchRequestDto userSearchRequestDto, ServiceStatusResponseDto status) {
        if(status.getCode() == 200) {
            List<User> users = userRepository.findByFilterWord(userSearchRequestDto.getFilterWord(), PageRequest.of(userSearchRequestDto.getPage(), userSearchRequestDto.getAmount()));

            if(users != null) {
                return new UserSearchResponseDto(nextHandler.handle(users, status), status);
            } else {
                status.setCode(204);
                status.setMessage("No content.");
            }
        }

        return new UserSearchResponseDto(Collections.emptyList(), status);
    }


}
