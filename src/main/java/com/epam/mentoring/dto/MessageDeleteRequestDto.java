package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.ChatExists;
import com.epam.mentoring.service.validator.annotation.MessageExists;
import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UserIsAuthorOfMessage;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@UserIsAuthorOfMessage
public class MessageDeleteRequestDto implements Serializable, MessageRequestDto {
    private static final long serialVersionUID = 994642621563252247L;

    @MessageExists
    private int messageId;

    @ChatExists
    private int chatId;

    @UserExists
    private int userId;
}
