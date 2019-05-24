package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.dto.ChatCreateRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.service.ChatServiceFacade;
import com.epam.mentoring.service.status.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "x-requested-with, x-requested-by, Authorization, Origin," +
                                                            " Content-Type")
public class ChatController {
    private ChatServiceFacade chatService;

    @Autowired
    public ChatController(ChatServiceFacade chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<ChatsResponseDto> getAllChatsForUser(HttpServletRequest request,
                                                               ChatsRequestDto chatsRequestDto) {
        chatsRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatsResponseDto allChatsForUser = chatService.findAllChatsForUser(chatsRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(allChatsForUser.getStatus()
                                                                                      .getCode()))
                .body(allChatsForUser);
    }

    @PostMapping
    public ResponseEntity<ChatInfoResponseDto> createNewChat(HttpServletRequest request,
                                                             @RequestBody ChatCreateRequestDto chatCreateRequestDto) {
        chatCreateRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatInfoResponseDto createdChat = chatService.createNewChat(chatCreateRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(createdChat.getStatus()
                                                                                      .getCode()))
                .body(createdChat);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatInfoResponseDto> getChatInfo(HttpServletRequest request,
                                                           ChatInfoRequestDto chatInfoRequestDto) {
        chatInfoRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatInfoResponseDto chatInfoResponseDto = chatService.getChatInfo(chatInfoRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(chatInfoResponseDto.getStatus()
                                                                                      .getCode()))
                .body(chatInfoResponseDto);
    }
}
