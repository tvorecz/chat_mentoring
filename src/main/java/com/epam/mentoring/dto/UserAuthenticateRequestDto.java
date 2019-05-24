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
public class UserAuthenticateRequestDto implements Serializable {
    private static final long serialVersionUID = -904646632577332349L;

    private String login;

    private String password;
}
