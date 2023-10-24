package com.branow.memoweb.controller;

import com.branow.memoweb.dto.user.UserLoginDto;
import com.branow.memoweb.dto.user.UserRegisterDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import com.branow.memoweb.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.branow.memoweb.controller.response.ResponseWrapper.*;

@RequiredArgsConstructor
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

    @GetMapping("/user")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        return wrapGet(() -> {
            String bearer = "bearer ";
            String headAuth = request.getHeader("Authorization");
            if (headAuth != null && headAuth.startsWith(bearer)) {
                String jwt = headAuth.replaceFirst(bearer, "");
                return authenticationService.getUser(jwt);
            } else {
                throw new IllegalStateException("Illegal Authorization header: " + headAuth);
            }
        });
    }

}
