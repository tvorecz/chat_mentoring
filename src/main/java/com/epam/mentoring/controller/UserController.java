package com.epam.mentoring.controller;

import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.dto.UsersSearchResponseDto;
import com.epam.mentoring.service.UserServiceFacade;
import com.epam.mentoring.service.status.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserServiceFacade userService;

    @Autowired
    public UserController(UserServiceFacade userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto registerResponseDto = userService.register(userRegisterRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(registerResponseDto.getStatus()
                                                                                      .getCode()))
                .body(registerResponseDto);
    }

    @GetMapping()
    public ResponseEntity<UsersSearchResponseDto> search(UsersSearchRequestDto usersSearchRequestDto) {
        UsersSearchResponseDto usersSearchResponseDto = userService.findUsers(usersSearchRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(usersSearchResponseDto.getStatus()
                                                                                      .getCode()))
                .body(usersSearchResponseDto);
    }


}
