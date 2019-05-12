package com.epam.mentoring.entity;

import lombok.*;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Messages")
public class Message implements Serializable {
    private static final long serialVersionUID = -8530528283541838445L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "chatId")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "dateCreation")
    private LocalDateTime dateOfCreation;

    @Column(name = "dateUpdating")
    private LocalDateTime dateOfUpdating;
}
