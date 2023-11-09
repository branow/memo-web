package com.branow.memoweb.controller;

import com.branow.memoweb.dto.module.ModuleSaveDto;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;
import static com.branow.memoweb.controller.response.ResponseWrapper.wrapPost;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/module")
@RestController
public class ModuleController {

    private final ModuleService moduleService;


    @DeleteMapping("/{moduleId}")
    public ResponseEntity<?> deleteByModuleId(HttpServletRequest request, @PathVariable Integer moduleId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            moduleService.deleteByJwtTokenAndModuleId(jwt, moduleId);
            return "Module was deleted successfully";
        });
    }

    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody ModuleSaveDto dto) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return moduleService.saveByJwtToken(jwt, dto);
        });
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetailsByModuleId(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getDetailsDtoByModuleId(id));
    }

    @GetMapping("/general-details/{id}")
    public ResponseEntity<?> getGeneralDetailsByModuleId(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getGeneralDetailsDtoByModuleId(id));
    }

}
