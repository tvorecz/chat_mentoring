package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
