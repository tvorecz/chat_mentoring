package com.epam.mentoring.service;

import com.epam.mentoring.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(User notSavedUser);

    User getById(int userId);

    List<User> findUsers();

    List<User> findUsers(List<Integer> participantsIds);

    List<User> findUsers(String filterWord);

    List<User> findUsers(String filterWord, Integer amount, Integer page);
}

