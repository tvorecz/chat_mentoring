package com.epam.mentoring.service;

import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.dto.UserSearchResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto);
    UserSearchResponseDto findUsers(UserSearchRequestDto userSearchRequestDto);
}

