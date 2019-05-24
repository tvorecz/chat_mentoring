package com.epam.mentoring.service.facade;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.entity.User;
import com.epam.mentoring.service.ServiceStorage;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.service.UserServiceFacade;
import com.epam.mentoring.service.mapper.MapperStorage;
import com.epam.mentoring.service.status.StatusResponse;
import com.epam.mentoring.service.status.StatusResponseFiller;
import com.epam.mentoring.service.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    private ServiceStorage serviceStorage;
    private StatusResponseFiller statusResponseFiller;
    private CommonValidator commonValidator;
    private MapperStorage mapperStorage;

    @Autowired
    public UserServiceFacadeImpl(ServiceStorage serviceStorage,
                                 StatusResponseFiller statusResponseFiller,
                                 CommonValidator commonValidator,
                                 MapperStorage mapperStorage) {
        this.serviceStorage = serviceStorage;
        this.statusResponseFiller = statusResponseFiller;
        this.commonValidator = commonValidator;
        this.mapperStorage = mapperStorage;
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(userRegisterRequestDto);

        User savedUser = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            User notSavedUser = User.builder()
                    .login(userRegisterRequestDto.getLogin())
                    .nickname(userRegisterRequestDto.getNickname())
                    .password(userRegisterRequestDto.getPassword())
                    .build();

            savedUser = serviceStorage.getUserService()
                    .register(notSavedUser);
        }

        return UserRegisterResponseDto.builder()
                .status(statusResponseDto)
                .user(mapperStorage.userToUserResponseDto()
                              .handle(savedUser))
                .build();
    }

    @Override
    public UsersSearchResponseDto findUsers(UsersSearchRequestDto usersSearchRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(usersSearchRequestDto);

        List<User> foundUsers = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            foundUsers = SearchCommandSwitcher.determine(usersSearchRequestDto)
                    .search(usersSearchRequestDto, serviceStorage.getUserService());

            if (foundUsers == null || foundUsers.size() == 0) {
                statusResponseFiller.setEmptyResponseStatus(statusResponseDto);
                foundUsers = null;
            }
        }

        return UsersSearchResponseDto.builder()
                .users(mapperStorage.userListToUserResponseDtoList()
                               .handle(foundUsers))
                .status(statusResponseDto)
                .build();
    }

    private interface SearchCommand {
        List<User> search(UsersSearchRequestDto usersSearchRequestDto, UserService userService);
    }

    private static class SearchCommandSwitcher {
        public static SearchCommand determine(UsersSearchRequestDto usersSearchRequestDto) {
            if (usersSearchRequestDto.getFilterWord() != null
                && usersSearchRequestDto.getFilterWord() != ""
                && usersSearchRequestDto.getAmount() == null
                && usersSearchRequestDto.getPage() == null) {
                return (UsersSearchRequestDto requestDto, UserService userService) -> {
                    return userService.findUsers(requestDto.getFilterWord());
                };
            }

            if (usersSearchRequestDto.getFilterWord() != null
                && usersSearchRequestDto.getFilterWord() != ""
                && usersSearchRequestDto.getAmount() != null
                && usersSearchRequestDto.getAmount() != null
                && usersSearchRequestDto.getAmount() > 0
                && usersSearchRequestDto.getPage() > 0) {
                return (UsersSearchRequestDto requestDto, UserService userService) -> {
                    return userService.findUsers(requestDto.getFilterWord(),
                                                 requestDto.getPage(),
                                                 requestDto.getAmount());
                };
            }

            return (UsersSearchRequestDto requestDto, UserService userService) -> {
                return userService.findUsers();
            };
        }
    }
}
