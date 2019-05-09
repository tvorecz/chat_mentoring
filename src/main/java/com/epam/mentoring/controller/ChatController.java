package com.epam.mentoring.controller;

import com.epam.mentoring.dto.ChatRequestDto;
import com.epam.mentoring.dto.ChatResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.service.ChatService;
import com.epam.mentoring.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
//    @Autowired
    private ChatService chatService;
//    @Autowired
    private MessageService messageService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ChatResponseDto>> getAllChatsForUser(@RequestParam int userId){
        List<ChatResponseDto> list = new ArrayList<>();
        list.add(ChatResponseDto.builder().title("New chat").author(new UserResponseDto()).id(1).build());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ChatResponseDto> createNewChat(@RequestBody ChatRequestDto chatRequestDto){
        ChatResponseDto chatResponseDto = ChatResponseDto.builder().author(UserResponseDto.builder().id(chatRequestDto.getAuthorId()).build()).id(chatRequestDto.getChatId()).title("New chatResponseDto").build();
        return ResponseEntity.ok(chatResponseDto);
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
