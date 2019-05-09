package com.epam.mentoring.service.handler.impl;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.dto.UserSearchResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class SearchUserHandler implements Handler<UserSearchRequestDto, UserSearchResponseDto> {
    private UserRepository userRepository;

    @Override
    public UserSearchResponseDto handle(UserSearchRequestDto userSearchRequestDto, ServiceStatusResponseDto status) {
        List<User> users;

        if(status.getCode() == 200) {
            users = userRepository.findBySearchWord(userSearchRequestDto.getFilterWord(), PageRequest.of(userSearchRequestDto.getPage(), userSearchRequestDto.getAmount()));

            if(users != null) {
                return new UserSearchResponseDto(status, users);
            } else {

            }

        } else {

        }

        return null;
    }


}
