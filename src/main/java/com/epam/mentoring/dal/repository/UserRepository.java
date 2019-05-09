package com.epam.mentoring.dal.repository;

import com.epam.mentoring.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.nickname LIKE %:filterWord% OR u.login LIKE %:filterWord% ORDER BY u.login ASC")
    List<User> findByFilterWord(@Param("filterWord") String filterWord, Pageable pageable);
}
