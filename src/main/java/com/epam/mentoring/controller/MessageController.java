package com.epam.mentoring.controller;

import com.epam.mentoring.dto.MessageCreateRequestDto;
import com.epam.mentoring.dto.MessageCreateResponseDto;
import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageHistoryResponseDto;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/chat/{chatId}/message")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<MessageHistoryResponseDto> getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto) {
        MessageHistoryResponseDto messageHistoryResponseDto = messageService.getChatHistory(messageHistoryRequestDto);

        return new ResponseEntity<>(messageHistoryResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageCreateResponseDto> createMessage(@PathVariable("userId") Integer userId,
                                                                  @PathVariable("chatId") Integer chatId,
                                                                  @RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageCreateRequestDto.setChatId(chatId);
        messageCreateRequestDto.setUserId(userId);

        MessageCreateResponseDto messageCreateResponseDto = messageService.createMessage(messageCreateRequestDto);

        return new ResponseEntity<>(messageCreateResponseDto, HttpStatus.OK);
    }
}
