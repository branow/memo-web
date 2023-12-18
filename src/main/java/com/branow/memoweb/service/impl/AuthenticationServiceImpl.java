package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.email.EmailDto;
import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.dto.verificationtoken.EmailTokenDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import com.branow.memoweb.exception.VerificationTokenExpiredException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.model.VerificationToken;
import com.branow.memoweb.service.*;
import com.branow.memoweb.util.RandomPasswordGenerator;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final EmailSenderService emailSenderService;
    private final RandomPasswordGenerator randomPasswordGenerator;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtBelongingChecker jwtBelongingChecker;

    @Override
    public UserJwtDto login(UserLoginDto dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        User user = (User) auth.getPrincipal();
        String jwt = jwtService.generateJwt(user);
        return userMapper.toUserJwtDto(user, jwt);
    }

    @Override
    public VerificationTokenDto register(UserRegisterDto dto) {
        User user = userService.save(userMapper.toUser(dto));
        String jwt = jwtService.generateVerificationJwt(user);
        return VerificationTokenDto.builder()
                .token(jwt)
                .build();
    }

    @Override
    public UserJwtDto enableUser(VerificationTokenDto dto) {
        String token = dto.getToken();
        if (jwtService.isExpired(token))
            throw new VerificationTokenExpiredException(jwtService.getExpirationDateTime(token));
        jwtService.getSubject(token);
        Integer userId = jwtBelongingChecker.getUserId(token);
        User user = userService.getByUserId(userId);
        user.setEnabled(true);
        userService.save(user);
        String jwt = jwtService.generateJwt(user);
        return userMapper.toUserJwtDto(user, jwt);
    }

    @Override
    public VerificationTokenDto regenerateToken(EmailTokenDto dto) {
        User user = userService.getByEmail(dto.getEmail());
        String token = jwtService.generateVerificationJwt(user);
        return VerificationTokenDto.builder()
                .token(token)
                .build();
    }

    @Override
    public void resetPassword(EmailDto dto) throws MessagingException {
        User user = userService.getByEmail(dto.getReceiver());
        String password = randomPasswordGenerator.generate();
        System.out.println("New Password: " + password);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        dto.setBody(dto.getBody().replace("{PASSWORD}", password));
        emailSenderService.send(dto);
    }
}
