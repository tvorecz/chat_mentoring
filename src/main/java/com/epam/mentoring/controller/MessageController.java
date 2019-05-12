package com.epam.mentoring.controller;

import com.epam.mentoring.dto.ChatInfoRequestDto;
import com.epam.mentoring.dto.ChatInfoResponseDto;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{userId}/chat/{chatId}/message")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<ChatInfoResponseDto> getMessage(ChatInfoRequestDto chatInfoRequestDto){

        return null;
    }
}
