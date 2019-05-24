package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.ChatExists;
import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UserInvolvedToChat;
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
@UserInvolvedToChat
public class MessageCreateRequestDto implements Serializable, UserInvolvedToChatDto {
    private static final long serialVersionUID = -4973216450409008124L;

    @ChatExists
    private int chatId;

    @UserExists
    private int userId;

    @NotEmpty
    @NotNull
    private String text;
}
