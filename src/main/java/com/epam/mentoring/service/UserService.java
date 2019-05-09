package com.epam.mentoring.service;

import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.dto.UserSearchResponseDto;

public interface UserService {
    UserSearchResponseDto findUsers(UserSearchRequestDto userSearchRequestDto);
}

