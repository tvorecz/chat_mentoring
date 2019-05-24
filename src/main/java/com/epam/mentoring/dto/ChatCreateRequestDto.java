package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.UserExists;
import com.epam.mentoring.service.validator.annotation.UsersExist;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ChatCreateRequestDto {
    @UserExists
    private int userId;
    @NotNull
    private String title;
    @UsersExist
    @NotNull
    private Integer[] participantsIds;
}
