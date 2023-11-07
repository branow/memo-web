package com.branow.memoweb.service;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.dto.verificationtoken.EmailTokenDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;

public interface AuthenticationService {
    UserJwtDto login(UserLoginDto dto);

    VerificationTokenDto register(UserRegisterDto dto);

    UserJwtDto enableUser(VerificationTokenDto verificationToken);

    UserPrivateShortDto getUser(String jwt);

    VerificationTokenDto regenerateToken(EmailTokenDto dto);

}
