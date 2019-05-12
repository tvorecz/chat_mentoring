package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.ChatExists;
import com.epam.mentoring.service.validator.annotation.CorrectPairOfDates;
import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UserInvolvedToChat;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@UserInvolvedToChat
@CorrectPairOfDates
public class MessageHistoryRequestDto implements UserInvolvedToChatDto {
    @UserExists
    private int userId;

    @ChatExists
    private int chatId;

    private LocalDateTime from;

    private LocalDateTime till;
}
