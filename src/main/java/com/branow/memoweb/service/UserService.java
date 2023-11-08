package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.model.User;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

public interface UserService extends UserDetailsService {

    UserSaveDto save(String jwt, UserSaveDto dto);

    User save(User user);

    User getByEmail(String email);

    UserDetailsDto getDetailsById(Integer id);

    UserPrivateShortDetailsDto getPrivateShortDetailsById(Integer id);

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsById(Integer id);

    UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id);

    UserDetailsDto getDetailsByJwtToken(String jwtToken);

    UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken);

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByJwtToken(String jwtToken);

    void delete(String jwt);
}
