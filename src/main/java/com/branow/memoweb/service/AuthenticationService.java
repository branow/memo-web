package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.LoginUserDto;
import com.branow.memoweb.dto.user.RegisterUserDto;
import com.branow.memoweb.dto.user.UserDto;
import com.branow.memoweb.dto.user.UserJwtDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;

public interface AuthenticationService {
    UserJwtDto login(LoginUserDto dto);

    VerificationTokenDto register(RegisterUserDto dto);

    UserJwtDto enableUser(VerificationTokenDto verificationToken);

    UserDto getUser(String jwt);

}
