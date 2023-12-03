package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserSaveDto save(String jwt, UserSaveDto dto);

    User save(User user);

    User getByEmail(String email);

    UserDetailsDto getDetailsDtoByUserId(Integer id);

    UserPrivateShortDetailsDto getPrivateShortDetailsDtoByUserId(Integer id);

    UserDetailsDto getDetailsDtoByJwtToken(String jwtToken);

    UserPrivateShortDetailsDto getPrivateShortDetailsDtoByJwtToken(String jwtToken);

    UserGeneralDetailsDto getGeneralDetailsDtoByJwtTokenAndUserId(String jwt, Integer userId);

    void deleteByJwtToken(String jwt);

    void changePassword(String jwt, ChangePasswordDto dto);

}
