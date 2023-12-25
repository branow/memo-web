package com.branow.memoweb.controller;

import com.branow.memoweb.dto.email.EmailDto;
import com.branow.memoweb.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapPost;

@RequiredArgsConstructor
@RequestMapping("/email")
@RestController
public class EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("")
    public ResponseEntity<?> send(@RequestBody EmailDto emailDto) {
        return wrapPost(() -> {
            emailSenderService.send(emailDto);
            return "Email was send successfully";
        });
    }

}
