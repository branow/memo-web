package com.branow.memoweb.controller;

import com.branow.memoweb.dto.user.UserSaveDto;
import com.branow.memoweb.service.UserService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;
import static com.branow.memoweb.controller.response.ResponseWrapper.wrapPost;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @DeleteMapping("")
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            userService.deleteByJwtToken(jwt);
            return "User was deleted successfully";
        });
    }

    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody UserSaveDto dto) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.save(jwt, dto);
        });
    }

    @GetMapping("/details")
    public ResponseEntity<?> getDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.getDetailsDtoByJwtToken(jwt);
        });
    }

    @GetMapping("/private-short-details")
    public ResponseEntity<?> getPrivateShortDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.getPrivateShortDetailsDtoByJwtToken(jwt);
        });
    }

    @GetMapping("/public-general-details/{id}")
    public ResponseEntity<?> getPublicGeneralDetailsByUserId(@PathVariable Integer id) {
        return wrapGet(() -> userService.getPublicGeneralDetailsDtoByUserId(id));
    }

    @GetMapping("/private-general-details")
    public ResponseEntity<?> getPrivateGeneralDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return userService.getPrivateGeneralDetailsDtoByJwtToken(jwt);
        });
    }

}
