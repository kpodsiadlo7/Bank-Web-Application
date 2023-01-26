package com.mainapp.service.mapper;

import com.mainapp.service.data.User;
import com.mainapp.web.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUserFromUserDto(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getRealName(),
                userDto.getPassword(),
                userDto.getPlainPassword(),
                userDto.getConfirmPassword(),
                userDto.getAuthorities()
        );
    }
}