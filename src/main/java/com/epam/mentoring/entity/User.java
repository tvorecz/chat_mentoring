package com.epam.mentoring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Users")
public class User implements Serializable {
    private static final long serialVersionUID = -1288154046796838743L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserChat> userChats;

}
