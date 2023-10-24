package com.branow.memoweb.controller;

import com.branow.memoweb.dto.user.LoginUserDto;
import com.branow.memoweb.dto.user.RegisterUserDto;
import com.branow.memoweb.dto.verificationtoken.VerificationTokenDto;
import com.branow.memoweb.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.branow.memoweb.controller.ResponseWrapper.wrap;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto dto) {
        return wrap(() -> authenticationService.register(dto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto dto) {
        return wrap(() -> authenticationService.login(dto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/enable")
    public ResponseEntity<?> enable(@RequestBody VerificationTokenDto dto) {
        return wrap(() -> authenticationService.enableUser(dto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        return wrap(() -> {
            String bearer = "bearer ";
            String headAuth = request.getHeader("Authorization");
            if (headAuth != null && headAuth.startsWith(bearer)) {
                String jwt = headAuth.replaceFirst(bearer, "");
                return authenticationService.getUser(jwt);
            } else {
                throw new IllegalStateException("Illegal Authorization header: " + headAuth);
            }
        }, HttpStatus.OK);
    }

}
