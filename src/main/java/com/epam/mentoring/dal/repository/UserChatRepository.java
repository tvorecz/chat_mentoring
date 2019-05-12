package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.UserChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserChatRepository extends CrudRepository<UserChat, Integer> {
    @Query("SELECT userChat FROM UserChat userChat WHERE userChat.userChatId.user.id = :idUser AND userChat.userChatId.chat.id = :idChat")
    Optional<UserChat> findByUserAndChatId(@Param("idUser") Integer idUser, @Param("idChat") Integer idChat);
}
