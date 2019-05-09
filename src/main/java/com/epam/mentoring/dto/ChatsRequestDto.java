package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.UserExists;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ChatsRequestDto implements Serializable {
    private static final long serialVersionUID = -8149236690079213364L;

    @UserExists
    private int userId;
}
