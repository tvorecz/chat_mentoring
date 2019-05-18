package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.dto.ChatCreateRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.service.ChatService;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "x-requested-with, x-requested-by, Authorization, Origin, Content-Type")
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<ChatsResponseDto> getAllChatsForUser(HttpServletRequest request,
                                                               ChatsRequestDto chatsRequestDto) {
        chatsRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatsResponseDto allChatsForUser = chatService.findAllChatsForUser(chatsRequestDto);

        return ResponseEntity.status(allChatsForUser.getStatus().getCode()).body(allChatsForUser);

//        return new ResponseEntity<>(allChatsForUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChatInfoResponseDto> createNewChat(HttpServletRequest request,
                                                             @RequestBody ChatCreateRequestDto chatCreateRequestDto) {
        chatCreateRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatInfoResponseDto createdChat = chatService.createNewChat(chatCreateRequestDto);

        return ResponseEntity.status(createdChat.getStatus().getCode()).body(createdChat);
//        return new ResponseEntity<>(createdChat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatInfoResponseDto> getChatInfo(HttpServletRequest request,
                                                           ChatInfoRequestDto chatInfoRequestDto) {
        chatInfoRequestDto.setUserId((Integer) request.getAttribute("userId"));

        ChatInfoResponseDto chatInfoResponseDto = chatService.getChatInfo(chatInfoRequestDto);

        return ResponseEntity.status(chatInfoResponseDto.getStatus().getCode()).body(chatInfoResponseDto);

//        return new ResponseEntity<>(chatInfoResponseDto, HttpStatus.OK);
    }
}
