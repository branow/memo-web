package com.branow.memoweb.controller;

import com.branow.memoweb.dto.user.ChangePasswordDto;
import com.branow.memoweb.dto.user.UserSaveDto;
import com.branow.memoweb.exception.AuthorizationHeaderParsingException;
import com.branow.memoweb.service.UserService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;
import static com.branow.memoweb.controller.response.ResponseWrapper.wrapPost;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @DeleteMapping("")
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            userService.deleteByJwtToken(jwt);
            return "User was deleted successfully";
        });
    }

    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody UserSaveDto dto) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return userService.save(jwt, dto);
        });
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody ChangePasswordDto dto) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            userService.changePassword(jwt, dto);
            return "Success";
        });
    }

    @GetMapping("/details")
    public ResponseEntity<?> getDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return userService.getDetailsDtoByJwtToken(jwt);
        });
    }

    @GetMapping("/private-short-details")
    public ResponseEntity<?> getPrivateShortDetails(HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return userService.getPrivateShortDetailsDtoByJwtToken(jwt);
        });
    }

    @GetMapping("/general-details/{userId}")
    public ResponseEntity<?> getGeneralDetails(@PathVariable Integer userId, HttpServletRequest request) {
        return wrapGet(() -> {
            String jwt = null;
            try {
                jwt = new HttpRequestHeaders(request).getJwt();
            } catch (AuthorizationHeaderParsingException e) {
            }
            return userService.getGeneralDetailsDtoByJwtTokenAndUserId(jwt, userId);
        });
    }

}
