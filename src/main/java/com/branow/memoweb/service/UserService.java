package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.UserPrivateShortDetailsDto;
import com.branow.memoweb.dto.user.UserPublicGeneralDetailsDto;
import com.branow.memoweb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    User getByEmail(String email);

    UserPrivateShortDetailsDto getPrivateShortDetailsByEmail(String email);

    UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken);

    UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id);
}
