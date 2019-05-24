package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.MessageServiceFacade;
import com.epam.mentoring.service.status.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/chat/{chatId}/message")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "x-requested-with, x-requested-by, Authorization, Origin," +
                                                            " Content-Type")
public class MessageController {
    private MessageServiceFacade messageService;

    @Autowired
    public MessageController(MessageServiceFacade messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<MessageHistoryResponseDto> getChatHistory(HttpServletRequest request,
                                                                    MessageHistoryRequestDto messageHistoryRequestDto) {
        messageHistoryRequestDto.setUserId((Integer) request.getAttribute("userId"));

        MessageHistoryResponseDto messageHistoryResponseDto = messageService.getChatHistory(messageHistoryRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(messageHistoryResponseDto.getStatus()
                                                                                      .getCode()))
                .body(messageHistoryResponseDto);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(HttpServletRequest request,
                                                            @PathVariable("chatId") Integer chatId,
                                                            @RequestBody MessageCreateRequestDto messageCreateRequestDto) {
        messageCreateRequestDto.setChatId(chatId);
        messageCreateRequestDto.setUserId((Integer) request.getAttribute("userId"));

        MessageResponseDto messageResponseDto = messageService.createMessage(messageCreateRequestDto);


        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(messageResponseDto.getStatus()
                                                                                      .getCode()))
                .body(messageResponseDto);
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

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(messageResponseDto.getStatus()
                                                                                      .getCode()))
                .body(messageResponseDto);
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

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(statusResponseDto.getStatus()
                                                                                      .getCode()))
                .body(statusResponseDto);
    }
}
