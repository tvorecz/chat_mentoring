package com.epam.mentoring.service;

import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> find(UserSearchRequestDto userSearchRequestDto);
}

