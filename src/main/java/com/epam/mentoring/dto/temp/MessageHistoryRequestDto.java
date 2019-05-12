package com.epam.mentoring.dto.temp;

import com.epam.mentoring.service.validator.annotation.ChatExists;
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
public class MessageHistoryRequestDto {

    @UserExists
    private int participantId;

    @ChatExists
    private int chatId;

    private LocalDateTime from;
    private LocalDateTime till;
}
