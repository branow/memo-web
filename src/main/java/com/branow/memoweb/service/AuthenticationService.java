package com.branow.memoweb.service;

import com.branow.memoweb.dto.email.EmailDto;
import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.dto.verificationtoken.EmailTokenDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import jakarta.mail.MessagingException;

public interface AuthenticationService {

    UserJwtDto login(UserLoginDto dto);

    VerificationTokenDto register(UserRegisterDto dto);

    UserJwtDto enableUser(VerificationTokenDto verificationToken);

    VerificationTokenDto regenerateToken(EmailTokenDto dto);

    void resetPassword(EmailDto dto) throws MessagingException;
}
