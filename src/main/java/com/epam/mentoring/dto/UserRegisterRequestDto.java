package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.LoginNotExists;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegisterRequestDto implements Serializable {
    private static final long serialVersionUID = 8917379861767939616L;

    @NotNull
    @LoginNotExists
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String nickname;
}
