package com.branow.memoweb.controller;

import com.branow.memoweb.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/module")
@RestController
public class ModuleController {

    private final ModuleService moduleService;


    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetailsByModuleId(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getDetailsDtoByModuleId(id));
    }

    @GetMapping("/general-details/{id}")
    public ResponseEntity<?> getGeneralDetailsByModuleId(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getGeneralDetailsDtoByModuleId(id));
    }

}
