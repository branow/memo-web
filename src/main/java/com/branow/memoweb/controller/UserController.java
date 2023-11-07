package com.branow.memoweb.controller;

import com.branow.memoweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/public/{id}")
    public ResponseEntity<?> getPublicById(@PathVariable Integer id) {
        return wrapGet(() -> userService.getPublicDtoById(id));
    }

}
