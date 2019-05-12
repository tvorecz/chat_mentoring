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

@RestController
@RequestMapping("/user/{userId}/chat")
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<ChatsResponseDto> getAllChatsForUser(ChatsRequestDto chatsRequestDto) {
        ChatsResponseDto allChatsForUser = chatService.findAllChatsForUser(chatsRequestDto);

        return new ResponseEntity<>(allChatsForUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChatInfoResponseDto> createNewChat(@PathVariable Integer userId,
                                                             @RequestBody ChatCreateRequestDto chatCreateRequestDto) {
        chatCreateRequestDto.setUserId(userId);

        ChatInfoResponseDto createdChat = chatService.createNewChat(chatCreateRequestDto);

        return new ResponseEntity<>(createdChat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatInfoResponseDto> getChatInfo(ChatInfoRequestDto chatInfoRequestDto) {
        ChatInfoResponseDto chatInfoResponseDto = chatService.getChatInfo(chatInfoRequestDto);

        return new ResponseEntity<>(chatInfoResponseDto, HttpStatus.OK);
    }
}
