package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.ChatExists;
import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UserInvolvedToChat;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@UserInvolvedToChat
public class ChatInfoRequestDto implements Serializable, UserInvolvedToChatDto {
    private static final long serialVersionUID = 5295276376898309781L;

    @UserExists
    private int userId;
    @ChatExists
    private int chatId;
}
