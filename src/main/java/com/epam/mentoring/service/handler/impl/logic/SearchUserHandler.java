package com.epam.mentoring.service.handler.impl.logic;

import com.epam.mentoring.dal.repository.UserRepository;
import com.epam.mentoring.dto.ServiceStatusResponseDto;
import com.epam.mentoring.dto.UserResponseDto;
import com.epam.mentoring.dto.UsersSearchRequestDto;
import com.epam.mentoring.dto.UsersSearchResponseDto;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.handler.Handler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchUserHandler implements Handler<UsersSearchRequestDto, UsersSearchResponseDto> {
    private UserRepository userRepository;
    private Handler<List<User>, List<UserResponseDto>> nextHandler;

    public SearchUserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public UsersSearchResponseDto handle(UsersSearchRequestDto usersSearchRequestDto, ServiceStatusResponseDto status) {
        if (status.getCode() == 200) {
            List<User> users = SearchCommandSwitcher.determine(usersSearchRequestDto)
                    .search(usersSearchRequestDto, userRepository);

            if (users != null) {
                return new UsersSearchResponseDto(nextHandler.handle(users, status), status);
            } else {
                status.setCode(204);
                status.setMessage("No content.");
            }
        }

        return new UsersSearchResponseDto(Collections.emptyList(), status);
    }

    private interface SearchCommand {
        List<User> search(UsersSearchRequestDto usersSearchRequestDto, UserRepository userRepository);
    }

    private static class SearchCommandSwitcher {
        public static SearchCommand determine(UsersSearchRequestDto usersSearchRequestDto) {
            if (usersSearchRequestDto.getFilterWord() != null
                && usersSearchRequestDto.getFilterWord() != ""
                && usersSearchRequestDto.getAmount() == null
                && usersSearchRequestDto.getPage() == null) {
                return (UsersSearchRequestDto requestDto, UserRepository userRepository) -> {
                    return userRepository.findByFilterWord(requestDto.getFilterWord());
                };
            }

            if (usersSearchRequestDto.getFilterWord() != null
                && usersSearchRequestDto.getFilterWord() != ""
                && usersSearchRequestDto.getAmount() != null
                && usersSearchRequestDto.getAmount() != null
                && usersSearchRequestDto.getAmount() > 0
                && usersSearchRequestDto.getPage() > 0) {
                return (UsersSearchRequestDto requestDto, UserRepository userRepository) -> {
                    return userRepository.findByFilterWord(requestDto.getFilterWord(), PageRequest.of(
                            requestDto.getPage() - 1, requestDto.getAmount()));
                };
            }

            return (UsersSearchRequestDto requestDto, UserRepository userRepository) -> {
                Iterable<User> users = userRepository.findAll();

                List<User> usersList = new ArrayList<>();

                for (User user : users) {
                    usersList.add(user);
                }

                return usersList;
            };
        }
    }

}
