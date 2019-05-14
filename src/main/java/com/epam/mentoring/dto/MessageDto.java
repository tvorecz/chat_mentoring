package com.epam.mentoring.dto;

import com.epam.mentoring.dto.UserResponseDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageDto implements Serializable {
    private static final long serialVersionUID = 5102871638699161083L;

    private int id;
    private int chatId;
    private UserResponseDto author;
    private String text;
    private LocalDateTime dateTimeOfCreating;
    private LocalDateTime dateTimeOfEditing;
}
