package com.epam.mentoring.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceStorage {
    private ChatService chatService;
    private UserService userService;
    private MessageService messageService;

    public ServiceStorage(ChatService chatService,
                          UserService userService,
                          MessageService messageService) {
        this.chatService = chatService;
        this.userService = userService;
        this.messageService = messageService;
    }

    public ChatService getChatService() {
        return chatService;
    }

    public UserService getUserService() {
        return userService;
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
