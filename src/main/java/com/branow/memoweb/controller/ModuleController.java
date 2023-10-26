package com.branow.memoweb.controller;

import com.branow.memoweb.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@RequestMapping("/module")
@RestController
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("/id-all-by-user-id/{userId}")
    public ResponseEntity<?> getIdAllByUserId(@PathVariable Integer userId) {
        return wrapGet(() -> moduleService.getIdAllByUserId(userId));
    }

    @GetMapping("/id-with-public-access-all-by-user-id/{userId}")
    public ResponseEntity<?> getIdWithPublicAccessAllByUserId(@PathVariable Integer userId) {
        return wrapGet(() -> moduleService.getIdWithPublicAccessAllByUserId(userId));
    }

    @GetMapping("/simple-by-id/{id}")
    public ResponseEntity<?> getSimpleById(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getSimpleDtoById(id));
    }

}
