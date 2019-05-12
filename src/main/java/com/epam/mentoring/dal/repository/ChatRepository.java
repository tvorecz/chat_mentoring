package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChatRepository extends CrudRepository<Chat, Integer> {

    @Query("select chat from Chat chat join chat.users users where users.id = :userId")
    List<Chat> findAllChatsForUser(@Param("userId") Integer userId);
}
