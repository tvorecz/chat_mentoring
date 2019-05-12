package com.epam.mentoring.dto.temp;

import com.epam.mentoring.dto.MessageResponseDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageHistoryResponseDto implements Serializable {
    private static final long serialVersionUID = -6052772413606786977L;

    private List<MessageResponseDto> history;
    private MessageResponseDto lastMessage;
}
