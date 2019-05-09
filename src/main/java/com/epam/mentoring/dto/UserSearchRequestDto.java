package com.epam.mentoring.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserSearchRequestDto implements Serializable {
    private static final long serialVersionUID = 8064746371843819993L;

    @Min(message = "Page number must be more than 0.", value = 1)
    private int userId;

    private String filterWord;

    @Min(message = "Amount must be more than 0.", value = 1)
    private int amount;

    @Min(message = "Page number must be more than 0.", value = 1)
    private int page;
}
