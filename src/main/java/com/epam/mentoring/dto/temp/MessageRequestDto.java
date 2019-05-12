package com.epam.mentoring.dto.temp;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageRequestDto {
    private int messageId;
    private int chatId;
    private int authorId;
    private String text;
    private LocalDateTime dateTime;
}
