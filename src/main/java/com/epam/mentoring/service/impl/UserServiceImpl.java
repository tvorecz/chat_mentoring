package com.epam.mentoring.service.impl;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.dto.UserSearchRequestDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<UserResponseDto> find(UserSearchRequestDto userSearchRequestDto) {

        List<User> users = userRepository.findBySearchWord(userSearchRequestDto.getFilterWord(), PageRequest.of(userSearchRequestDto.getPage(), userSearchRequestDto.getAmount()));

        Set<ConstraintViolation<UserSearchRequestDto>> violations = validator.validate(userSearchRequestDto);

        
        if(users != null) {

        } else {
            return Collections.emptyList();
        }
    }
}
