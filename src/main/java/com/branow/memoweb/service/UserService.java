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

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsDtoByUserId(Integer id);

    UserPublicGeneralDetailsDto getPublicGeneralDetailsDtoByUserId(Integer id);

    UserDetailsDto getDetailsDtoByJwtToken(String jwtToken);

    UserPrivateShortDetailsDto getPrivateShortDetailsDtoByJwtToken(String jwtToken);

    UserPrivateGeneralDetailsDto getPrivateGeneralDetailsDtoByJwtToken(String jwtToken);

    void deleteByJwtToken(String jwt);

    void changePassword(String jwt, ChangePasswordDto dto);
}
