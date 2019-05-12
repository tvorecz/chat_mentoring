package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.chat.id = :chatId")
    List<Message> findMessagesInChatFromDate(@Param("fromDate") LocalDate fromDate, @Param("chatId") Integer chatId);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.dateOfCreation <= :tillDate AND message.chat.id = :chatId")
    List<Message> findMessegesInChatBetweenDates(@Param("fromDate") LocalDate fromDate, @Param("tillDate") LocalDate tillDate, @Param("chatId") Integer chatId);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.chat.id = :chatId AND message.user.id = :userId")
    List<Message> findUserMessagesInChatFromDate(@Param("fromDate") LocalDate fromDate, @Param("chatId") Integer chatId, @Param("userId") Integer userID);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.dateOfCreation <= :tillDate AND message.chat.id = :chatId AND message.user.id = :userId")
    List<Message> findUserMessegesInChatBetweenDates(@Param("fromDate") LocalDate fromDate, @Param("tillDate") LocalDate tillDate, @Param("chatId") Integer chatId, @Param("userId") Integer userID);
}
