package com.epam.mentoring.service;

import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.dto.UsersSearchResponseDto;

public interface UserServiceFacade {
    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto);

    UsersSearchResponseDto findUsers(UsersSearchRequestDto usersSearchRequestDto);
}
