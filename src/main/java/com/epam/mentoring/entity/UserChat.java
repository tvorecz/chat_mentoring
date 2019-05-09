package com.epam.mentoring.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "UserChat")
public class UserChat implements Serializable {
    private static final long serialVersionUID = 834384425386513969L;

    @EmbeddedId
    private UserChatId userChatId;

    @Column(name = "role")
    private UserRoleInChat role;
}
