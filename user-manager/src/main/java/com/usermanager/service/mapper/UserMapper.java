package com.usermanager.service.mapper;

import com.usermanager.domain.UserEntity;
import com.usermanager.service.data.User;
import com.usermanager.web.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto mapToUserDtoFromUser(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getRealName(),
                user.getPassword(),
                null
        );
    }

    public User mapToUserFromUserDto(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getRealName(),
                userDto.getPassword(),
                userDto.getConfirmPassword()
        );
    }

    public UserEntity mapToUserEntityFromUser(final User user) {
        return new UserEntity(
                user.getUsername(),
                user.getRealName(),
                user.getPassword()
        );
    }

    public User mapToUserFromUserEntity(final UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getRealName(),
                userEntity.getPassword(),
                null
        );
    }
}
