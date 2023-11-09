package com.branow.memoweb.controller;

import com.branow.memoweb.dto.user.UserLoginDto;
import com.branow.memoweb.dto.user.UserRegisterDto;
import com.branow.memoweb.dto.verificationtoken.EmailTokenDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import com.branow.memoweb.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto dto) {
        return wrapPost(() -> authenticationService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        return wrapPost(() -> authenticationService.login(dto));
    }

    @PostMapping("/enable")
    public ResponseEntity<?> enable(@RequestBody VerificationTokenDto dto) {
        return wrapPost(() -> authenticationService.enableUser(dto));
    }

    @PostMapping("/regenerate-token")
    public ResponseEntity<?> regenerateToken(@RequestBody EmailTokenDto dto) {
        return wrapPost(() -> authenticationService.regenerateToken(dto));
    }

}
