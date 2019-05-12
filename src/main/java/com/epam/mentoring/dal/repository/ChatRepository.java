package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ChatRepository extends CrudRepository<Chat, Integer> {

    @Query("select chat from Chat chat join chat.users users where users.id = :userId")
    List<Chat> findAllChatsForUser(@Param("userId") Integer userId);

    @Query("select chat, messages from Chat chat left join chat.messages messages on messages.dateOfCreation >= :minDateTime where chat.id = :chatId")
    Optional<List<Object[]>> findChatInfoByChatIdWithLastMessages(@Param("chatId") Integer chatId, @Param("minDateTime")LocalDateTime minDateTime);
}
