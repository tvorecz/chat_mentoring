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
public class LoginResponseDto implements Serializable {
    private static final long serialVersionUID = -7886261567573519037L;

    private UserResponseDto user;
    private ServiceStatusResponseDto status;
}
