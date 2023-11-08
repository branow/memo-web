package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.user.UserPrivateShortDetailsDto;
import com.branow.memoweb.dto.user.UserRegisterDto;
import com.branow.memoweb.dto.user.UserDto;
import com.branow.memoweb.dto.user.UserJwtDto;
import com.branow.memoweb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserJwtDto toUserJwtDto(User user, String jwt) {
        return UserJwtDto.builder()
                .user(toUserPrivateShortDto(user))
                .jwt(jwt)
                .build();
    }


    public UserPrivateShortDetailsDto toUserPrivateShortDto(User user) {
        return UserPrivateShortDetailsDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enable(user.isEnabled())
                .build();
    }


    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .description(user.getDescription())
                .enable(user.isEnabled())
                .build();
    }

    public User toUser(UserRegisterDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }

}
