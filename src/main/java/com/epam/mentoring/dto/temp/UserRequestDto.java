package com.epam.mentoring.dto.temp;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequestDto implements Serializable {
    private static final long serialVersionUID = -2475706901291599677L;

    private int id;
    private String login;
    private String password;
    private String token;
}
