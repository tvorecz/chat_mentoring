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
public class UserSearchResponseDto implements Serializable {
    private static final long serialVersionUID = -4475960023692183366L;

    private List<UserResponseDto> users;
    private ServiceStatusResponseDto status;

}
