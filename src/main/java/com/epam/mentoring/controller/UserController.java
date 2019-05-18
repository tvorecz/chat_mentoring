package com.epam.mentoring.controller;

import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.dto.UsersSearchResponseDto;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, allowedHeaders = "x-requested-with, x-requested-by, Authorization, Origin, Content-Type")
//@CrossOrigin
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        UserRegisterResponseDto registerResponseDto = userService.register(userRegisterRequestDto);

        return ResponseEntity.status(registerResponseDto.getStatus().getCode()).body(registerResponseDto);
//        return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<UsersSearchResponseDto> search(UsersSearchRequestDto usersSearchRequestDto) {
        UsersSearchResponseDto usersSearchResponseDto = userService.findUsers(usersSearchRequestDto);

        return ResponseEntity.status(usersSearchResponseDto.getStatus().getCode()).body(usersSearchResponseDto);
//        return new ResponseEntity<>(usersSearchResponseDto, HttpStatus.OK);
    }

    
}
