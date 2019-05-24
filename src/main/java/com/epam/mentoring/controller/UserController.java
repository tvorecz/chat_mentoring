package com.epam.mentoring.controller;

import com.epam.mentoring.dto.UserRegisterRequestDto;
import com.epam.mentoring.dto.UserRegisterResponseDto;
import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.dto.UsersSearchResponseDto;
import com.epam.mentoring.service.UserServiceFacade;
import com.epam.mentoring.service.status.StatusResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterWord", value = "Word for search by login and nickname", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "Amount of records in response", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Number of page< starting with 1", required = false, dataType = "string", paramType = "query")
    })
    public ResponseEntity<UsersSearchResponseDto> search(UsersSearchRequestDto usersSearchRequestDto) {
        UsersSearchResponseDto usersSearchResponseDto = userService.findUsers(usersSearchRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(usersSearchResponseDto.getStatus()
                                                                                      .getCode()))
                .body(usersSearchResponseDto);
    }


}
