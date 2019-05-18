package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chat/{chatId}/message")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "x-requested-with, x-requested-by, Authorization, Origin, Content-Type")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<MessageHistoryResponseDto> getChatHistory(HttpServletRequest request,
                                                                    MessageHistoryRequestDto messageHistoryRequestDto) {
        messageHistoryRequestDto.setUserId((Integer) request.getAttribute("userId"));

        MessageHistoryResponseDto messageHistoryResponseDto = messageService.getChatHistory(messageHistoryRequestDto);

        return ResponseEntity.status(messageHistoryResponseDto.getStatus()
                                             .getCode())
                .body(messageHistoryResponseDto);

//        return new ResponseEntity<>(messageHistoryResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(HttpServletRequest request,
                                                            @PathVariable("chatId") Integer chatId,
                                                            @RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageCreateRequestDto.setChatId(chatId);
        messageCreateRequestDto.setUserId((Integer) request.getAttribute("userId"));

        MessageResponseDto messageResponseDto = messageService.createMessage(messageCreateRequestDto);


        return ResponseEntity.status(messageResponseDto.getStatus()
                                             .getCode())
                .body(messageResponseDto);
//        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> updateMessage(HttpServletRequest request,
                                                            @PathVariable("chatId") Integer chatId,
                                                            @PathVariable("messageId") Integer messageId,
                                                            @RequestBody MessageUpdateRequestDto messageUpdateRequestDto) {
        messageUpdateRequestDto.setChatId(chatId);
        messageUpdateRequestDto.setUserId((Integer) request.getAttribute("userId"));
        messageUpdateRequestDto.setMessageId(messageId);

        MessageResponseDto messageResponseDto = messageService.updateMessage(messageUpdateRequestDto);

        return ResponseEntity.status(messageResponseDto.getStatus()
                                             .getCode())
                .body(messageResponseDto);
//        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<StatusResponseDto> deleteMessage(HttpServletRequest request,
                                                           @PathVariable("chatId") Integer chatId,
                                                           @PathVariable("messageId") Integer messageId) {
        MessageDeleteRequestDto messageDeleteRequestDto = MessageDeleteRequestDto.builder()
                .messageId(messageId)
                .chatId(chatId)
                .userId((Integer) (request.getAttribute("userId")))
                .build();

        StatusResponseDto statusResponseDto = messageService.deleteMessage(messageDeleteRequestDto);

        return ResponseEntity.status(statusResponseDto.getStatus()
                                             .getCode())
                .body(statusResponseDto);
//        return new ResponseEntity<>(serviceStatusResponseDto, HttpStatus.OK);
    }
}
