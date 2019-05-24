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
public class UserRegisterResponseDto implements Serializable {
    private static final long serialVersionUID = 6916496818356518807L;

    private UserResponseDto user;
    private ServiceStatusResponseDto status;
}
