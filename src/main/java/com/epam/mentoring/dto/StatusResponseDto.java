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
public class StatusResponseDto implements Serializable {
    private static final long serialVersionUID = 4135620239672356146L;

    private ServiceStatusResponseDto status;
}
