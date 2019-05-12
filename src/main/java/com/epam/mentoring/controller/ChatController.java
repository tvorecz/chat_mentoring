package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.dto.temp.ChatRequestDto;
import com.epam.mentoring.dto.ChatResponseDto;
import com.epam.mentoring.service.ChatService;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
//    @Autowired
    private ChatService chatService;
//    @Autowired
    private MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ChatsResponseDto> getAllChatsForUser(ChatsRequestDto chatsRequestDto){
        ChatsResponseDto allChatsForUser = chatService.findAllChatsForUser(chatsRequestDto);

        return new ResponseEntity<>(allChatsForUser, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ChatResponseDto> createNewChat(@RequestBody ChatRequestDto chatRequestDto){

        return null;
    }

    @GetMapping("/{chatId}")
    @ResponseBody
    public ResponseEntity<ChatResponseDto> getChatInfo(ChatRequestDto chatRequestDto){

        return null;
    }

    @GetMapping("/{chatId}/message")
    @ResponseBody
    public ResponseEntity<ChatResponseDto> getChatInfo(ChatResponseDto chatResponseDto){

        return null;
    }
}
