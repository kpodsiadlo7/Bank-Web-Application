package com.proposalmanager.service;

import com.proposalmanager.service.data.User;
import com.proposalmanager.service.mapper.UserMapper;
import com.proposalmanager.web.feign.FeignServiceUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final FeignServiceUserManager feignServiceUserManager;
    private final UserMapper userMapper;

    public User fetchUserById(final Long userId, final User error) {
        log.warn("fetch user by id");
        try {
            return userMapper.mapToUserFromUserDto(feignServiceUserManager.getUserById(userId));
        } catch (Exception e) {
            log.warn("problem with connecting to user manager");
            error.setId(-1L);
            error.setRealName("There is a problem with connecting to user manager");
            return error;
        }
    }
}
