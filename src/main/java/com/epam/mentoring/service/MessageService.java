package com.epam.mentoring.service;

import com.epam.mentoring.dto.MessageHistoryRequestDto;
import com.epam.mentoring.dto.MessageHistoryResponseDto;
import com.epam.mentoring.dto.MessageRequestDto;
import com.epam.mentoring.dto.MessageResponseDto;

import java.util.List;

public interface MessageService {
    MessageHistoryResponseDto getMessages(MessageHistoryRequestDto messageHistoryRequestDto);
}
