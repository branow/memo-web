package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.UserDetailsDto;
import com.branow.memoweb.dto.user.UserPrivateGeneralDetailsDto;
import com.branow.memoweb.dto.user.UserPrivateShortDetailsDto;
import com.branow.memoweb.dto.user.UserPublicGeneralDetailsDto;
import com.branow.memoweb.model.User;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

public interface UserService extends UserDetailsService {
    User save(User user);

    User getByEmail(String email);

    UserDetailsDto getDetailsByJwtToken(String jwtToken);

    UserDetailsDto getDetailsByEmail(String email);

    UserPrivateShortDetailsDto getPrivateShortDetailsByEmail(String email);

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByEmail(String email);

    UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken);

    UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id);

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByJwtToken(String jwtToken);

}
