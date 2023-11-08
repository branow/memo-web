package com.branow.memoweb.controller;

import com.branow.memoweb.service.UserService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/private-short-details")
    public ResponseEntity<?> getPrivateShortDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.getPrivateShortDetailsByJwtToken(jwt);
        });
    }

    @GetMapping("/public-general-details/{id}")
    public ResponseEntity<?> getPublicGeneralDetails(@PathVariable Integer id) {
        return wrapGet(() -> userService.getPublicGeneralDetailsById(id));
    }

    @GetMapping("/private-general-details")
    public ResponseEntity<?> getPrivateGeneralDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.getPrivateGeneralDetailsByJwtToken(jwt);
        });
    }

}
