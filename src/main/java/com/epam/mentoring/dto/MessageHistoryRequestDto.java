package com.epam.mentoring.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageHistoryRequestDto {
    private int participantId;
    private int chatId;
    private LocalDateTime fromIncluding;
    private LocalDateTime tillExcluding;
}
