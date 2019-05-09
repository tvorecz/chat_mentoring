package com.epam.mentoring.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ChatRequestDto {
    private int chatId;
    private String title;
    private int authorId;
    private int[] participants;
}
