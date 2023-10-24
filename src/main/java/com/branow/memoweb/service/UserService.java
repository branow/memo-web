package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.UserPrivateShortDto;
import com.branow.memoweb.dto.user.UserPublicDto;
import com.branow.memoweb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    User getByEmail(String email);

    UserPrivateShortDto getPrivateShortByEmail(String email);

    UserPublicDto getPublicDtoById(Integer id);
}
