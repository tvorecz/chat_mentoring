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
public class ChatsResponseDto implements Serializable {
    private static final long serialVersionUID = -4463953008894412867L;

    private List<ChatInfoDto> chats;
    private ServiceStatusResponseDto status;

}
