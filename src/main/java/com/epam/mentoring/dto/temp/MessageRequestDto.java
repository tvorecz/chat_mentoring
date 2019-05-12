package com.epam.mentoring.dto.temp;

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
public class MessageRequestDto implements Serializable {
    private static final long serialVersionUID = -3670642423323844520L;

    private int messageId;
    private int chatId;
    private int authorId;
    private String text;
    private LocalDateTime dateTime;
}
