package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.dto.verificationtoken.EmailTokenDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import com.branow.memoweb.exception.VerificationTokenExpiredException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.model.VerificationToken;
import com.branow.memoweb.service.AuthenticationService;
import com.branow.memoweb.service.UserService;
import com.branow.memoweb.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final JwtTokenService jwtTokenService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserJwtDto login(UserLoginDto dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        User user = (User) auth.getPrincipal();
        String jwt = jwtTokenService.generateJwt(user);
        return userMapper.toUserJwtDto(user, jwt);
    }

    @Override
    public VerificationTokenDto register(UserRegisterDto dto) {
        User user = userService.save(userMapper.toUser(dto));
        VerificationToken token = verificationTokenService.createToken(user);
        return VerificationTokenDto.builder()
                .token(token.getToken())
                .build();
    }

    @Override
    public UserJwtDto enableUser(VerificationTokenDto dto) {
        VerificationToken token = verificationTokenService.getByToken(dto.getToken());
        if (token.getExpiration().isBefore(LocalDateTime.now())) {
            throw new VerificationTokenExpiredException(token);
        }
        User user = token.getUser();
        verificationTokenService.delete(token);
        user.setEnabled(true);
        userService.save(user);
        String jwt = jwtTokenService.generateJwt(user);
        return userMapper.toUserJwtDto(user, jwt);
    }

    @Override
    public VerificationTokenDto regenerateToken(EmailTokenDto dto) {
        User user = userService.getByEmail(dto.getEmail());
        verificationTokenService.deleteByToken(dto.getToken());
        VerificationToken token = verificationTokenService.createToken(user);
        return VerificationTokenDto.builder()
                .token(token.getToken())
                .build();
    }
}
