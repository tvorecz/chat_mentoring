package com.epam.mentoring.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = 8371762598185620735L;

    private int id;
    private String login;
    private String nickname;
}
