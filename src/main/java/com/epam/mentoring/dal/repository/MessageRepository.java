package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query("select message from Message message where message.id = :messageId")
    Message getCreatedMessage(@Param("messageId") Integer messageId);

    Optional<Message> findByIdAndChat_IdAndUser_Id(int messageId, int chatId, int userId);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.chat.id = " +
           ":chatId order by message.dateOfCreation")
    List<Message> getChatHistoryFromDate(@Param("chatId") Integer chatId, @Param("fromDate") LocalDateTime fromDate);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.dateOfCreation " +
           "< :tillDate AND message.chat.id = :chatId  order by message.dateOfCreation")
    List<Message> getChatHistoryBetweenDates(@Param("chatId") Integer chatId,
                                             @Param("fromDate") LocalDateTime fromDate,
                                             @Param("tillDate") LocalDateTime tillDate);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.chat.id = " +
           ":chatId AND message.user.id = :userId  order by message.dateOfCreation")
    List<Message> getChatHistoryFromDateForUser(@Param("chatId") Integer chatId,
                                                @Param("userId") Integer userId,
                                                @Param("fromDate") LocalDateTime fromDate);

    @Query("SELECT message FROM Message message WHERE message.dateOfCreation >= :fromDate AND message.dateOfCreation " +
           "< :tillDate AND message.chat.id = :chatId AND message.user.id = :userId order by message.dateOfCreation")
    List<Message> getChatHistoryBetweenDatesForUser(@Param("chatId") Integer chatId,
                                                    @Param("userId") Integer userId,
                                                    @Param("fromDate") LocalDateTime fromDate,
                                                    @Param("tillDate") LocalDateTime tillDate);
}
