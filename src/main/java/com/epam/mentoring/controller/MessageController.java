package com.epam.mentoring.controller;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.service.MessageServiceFacade;
import com.epam.mentoring.service.status.StatusResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatId", value = "Chat's id", required = true, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "from", value = "Start date including", required = false, dataType = "date", paramType = "query"),
            @ApiImplicitParam(name = "till", value = "End date excluding", required = false, dataType = "date", paramType = "query")
    })
    public ResponseEntity<MessageHistoryResponseDto> getChatHistory(HttpServletRequest request,
                                                                    MessageHistoryRequestDto messageHistoryRequestDto) {
        messageHistoryRequestDto.setUserId((Integer) request.getAttribute("userId"));

        MessageHistoryResponseDto messageHistoryResponseDto = messageService.getChatHistory(messageHistoryRequestDto);

        return ResponseEntity.status(StatusResponse.getHttpStatusByCustomCode(messageHistoryResponseDto.getStatus()
                                                                                      .getCode()))
                .body(messageHistoryResponseDto);
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatId", value = "Chat's id", required = true, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "Text of message", required = true, dataType = "string", paramType = "query")
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatId", value = "Chat's id", required = true, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "Text for update", required = true, dataType = "string", paramType = "query")
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatId", value = "Chat's id", required = true, dataType = "integer", paramType = "query")
    })
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
