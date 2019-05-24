package com.epam.mentoring.service.logic;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(User notSavedUser) {
        notSavedUser.setPassword(passwordEncoder.encode(notSavedUser.getPassword()));

        User savedUser = userRepository.save(notSavedUser);

        return savedUser;
    }

    @Override
    public User getById(int userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.isPresent() ? user.get() : null;
    }

    @Override
    public List<User> findUsers() {
        return createListOfChatMembers(userRepository.findAll());
    }

    @Override
    public List<User> findUsers(List<Integer> participantsIds) {
        return createListOfChatMembers(userRepository.findAllById(participantsIds));
    }

    @Override
    public List<User> findUsers(String filterWord) {
        List<User> usersList = userRepository.findByFilterWord(filterWord);

        return usersList;
    }

    @Override
    public List<User> findUsers(String filterWord, Integer amount, Integer page) {
        List<User> usersList = userRepository.findByFilterWord(filterWord, PageRequest.of(page - 1, amount));

        return usersList;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);

        return user.orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
    }


    private List<User> createListOfChatMembers(Iterable<User> users) {
        List<User> members = new ArrayList<>();

        for (User user : users) {
            members.add(user);
        }

        return members;
    }
}
