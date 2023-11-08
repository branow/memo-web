package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public UserSaveDto toUserSaveDto(User user) {
        return UserSaveDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .description(user.getDescription())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserSaveDto dto, String password, boolean enabled) {
        return User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .description(dto.getDescription())
                .password(password)
                .enabled(enabled)
                .build();
    }

    public UserDetailsDto toUserDetailsDto(UserDetailsRepositoryDto dto) {
        return UserDetailsDto.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .description(dto.getDescription())
                .build();
    }

    public UserPrivateShortDetailsDto toUserPrivateShortDetailsDto(UserPrivateShortDetailsRepositoryDto dto) {
        return UserPrivateShortDetailsDto.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .enabled(dto.getEnabled())
                .build();
    }

    public UserPublicGeneralDetailsDto toUserPublicGeneralDetailsDto(UserGeneralDetailsRepositoryDto dto, List<Integer> moduleIds) {
        return UserPublicGeneralDetailsDto.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .description(dto.getDescription())
                .publicModuleIds(moduleIds)
                .build();
    }

    public UserPrivateGeneralDetailsDto toUserPrivateGeneralDetailsDto(UserGeneralDetailsRepositoryDto dto, List<Integer> moduleIds) {
        return UserPrivateGeneralDetailsDto.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .description(dto.getDescription())
                .moduleIds(moduleIds)
                .build();
    }

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
                .enabled(user.isEnabled())
                .build();
    }


    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .description(user.getDescription())
                .enabled(user.isEnabled())
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
