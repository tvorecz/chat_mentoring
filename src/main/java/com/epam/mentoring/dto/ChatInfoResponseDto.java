package com.epam.mentoring.dto;

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
public class ChatInfoResponseDto implements Serializable {
    private static final long serialVersionUID = 918281995593131439L;

    private ChatInfoDto chat;
    private List<MessageDto> lastHistory;
    private ServiceStatusResponseDto status;
}
