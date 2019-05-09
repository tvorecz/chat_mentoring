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
public class ServiceStatusResponseDto implements Serializable {
    private static final long serialVersionUID = -5290108680582592467L;

    private int code;
    private String message;
}
