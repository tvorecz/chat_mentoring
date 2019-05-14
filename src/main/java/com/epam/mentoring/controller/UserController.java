package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto registerResponseDto = userService.register(userRegisterRequestDto);

        return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<UsersSearchResponseDto> search(UsersSearchRequestDto usersSearchRequestDto) {
        UsersSearchResponseDto usersSearchResponseDto = userService.findUsers(usersSearchRequestDto);

        return new ResponseEntity<>(usersSearchResponseDto, HttpStatus.OK);
    }

    
}
