package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
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
    public ResponseEntity<MessageResponseDto> createMessage(@PathVariable("userId") Integer userId,
                                                            @PathVariable("chatId") Integer chatId,
                                                            @RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageCreateRequestDto.setChatId(chatId);
        messageCreateRequestDto.setUserId(userId);

        MessageResponseDto messageResponseDto = messageService.createMessage(messageCreateRequestDto);

        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> updateMessage(@PathVariable("userId") Integer userId,
                                                            @PathVariable("chatId") Integer chatId,
                                                            @PathVariable("messageId") Integer messageId,
                                                            @RequestBody MessageUpdateRequestDto messageUpdateRequestDto) {
        messageUpdateRequestDto.setChatId(chatId);
        messageUpdateRequestDto.setUserId(userId);
        messageUpdateRequestDto.setMessageId(messageId);

        MessageResponseDto messageResponseDto = messageService.updateMessage(messageUpdateRequestDto);

        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ServiceStatusResponseDto> deleteMessage(@PathVariable("userId") Integer userId,
                                                                  @PathVariable("chatId") Integer chatId,
                                                                  @PathVariable("messageId") Integer messageId) {
        MessageDeleteRequestDto messageDeleteRequestDto = MessageDeleteRequestDto.builder()
                .messageId(messageId)
                .chatId(chatId)
                .userId(userId)
                .build();

        ServiceStatusResponseDto serviceStatusResponseDto = messageService.deleteMessage(messageDeleteRequestDto);


        return new ResponseEntity<>(serviceStatusResponseDto, HttpStatus.OK);
    }
}
