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
public class MessageResponseDto implements Serializable {
    private static final long serialVersionUID = 255354544789265990L;

    private MessageDto message;
    private ServiceStatusResponseDto status;
}
