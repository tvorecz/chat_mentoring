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
public class ChatInfoDto implements Serializable {
    private static final long serialVersionUID = -1988846493056362337L;

    private int id;
    private String title;
    private List<UserResponseDto> participants;
}
