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
@Table(name = "Chats")
public class Chat implements Serializable {
    private static final long serialVersionUID = 6520100087794572992L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatId")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<UserChat> userChats;
}
