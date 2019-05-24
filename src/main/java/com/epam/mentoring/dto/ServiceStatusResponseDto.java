package com.epam.mentoring.dto;

import com.epam.mentoring.service.status.StatusResponse;
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

    @Builder.Default
    private int code = StatusResponse.SUCCESS.getCode();
    @Builder.Default
    private String message = "Success.";
}
