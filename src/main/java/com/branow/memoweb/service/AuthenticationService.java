package com.branow.memoweb.service;

import com.branow.memoweb.dto.authentication.*;

public interface AuthenticationService {
    UserJwtDto login(LoginUserDto dto);

    VerificationTokenDto register(RegisterUserDto dto);

    UserJwtDto enableUser(VerificationTokenDto verificationToken);

    UserDto getUser(String jwt);

}
