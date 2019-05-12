package com.epam.mentoring.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegisterResponseDto implements Serializable {
    private static final long serialVersionUID = 6916496818356518807L;

    private ServiceStatusResponseDto status;
    private Map<String, String> links;
}
