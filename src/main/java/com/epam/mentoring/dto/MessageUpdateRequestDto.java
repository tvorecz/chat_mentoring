package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.ChatExists;
import com.epam.mentoring.service.validator.annotation.MessageExists;
import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UserIsAuthorOfMessage;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@UserIsAuthorOfMessage
public class MessageUpdateRequestDto implements Serializable, MessageRequestDto {
    private static final long serialVersionUID = -8481918510016246281L;

    @MessageExists
    private int messageId;

    @ChatExists
    private int chatId;

    @UserExists
    private int userId;

    @NotEmpty
    @NotNull
    private String text;



}
