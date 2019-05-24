package com.epam.mentoring.service.mapper.response;

import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.mapper.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapper implements DtoMapper<User, UserResponseDto> {
    @Override
    public UserResponseDto handle(User entity) {
        if(entity != null) {
            return UserResponseDto.builder()
                    .id(entity.getId())
                    .login(entity.getLogin())
                    .nickname(entity.getNickname())
                    .build();
        } else {
            return null;
        }
    }
}
