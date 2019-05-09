package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
