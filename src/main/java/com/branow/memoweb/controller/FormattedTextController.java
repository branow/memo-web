package com.branow.memoweb.controller;

import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/formatted-text")
@RestController
public class FormattedTextController {

    private final FormattedTextService formattedTextService;


    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody FormattedTextSaveDto dto) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return formattedTextService.saveWithJwtCheck(jwt, dto);
        });
    }

}
