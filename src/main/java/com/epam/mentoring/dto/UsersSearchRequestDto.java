package com.epam.mentoring.dto;

import com.epam.mentoring.service.validator.annotation.PageAndAmoutOfItemsIsCorrect;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@PageAndAmoutOfItemsIsCorrect
public class UsersSearchRequestDto implements Serializable {
    private static final long serialVersionUID = 8064746371843819993L;

    private String filterWord;

    private Integer amount;

    private Integer page;
}
