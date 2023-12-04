package com.branow.memoweb.controller;

import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleSaveDto;
import com.branow.memoweb.model.AccessType;
import com.branow.memoweb.model.auxilary.Access;
import com.branow.memoweb.service.JwtBelongingChecker;
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
    private final JwtBelongingChecker checker;


    @DeleteMapping("/{moduleId}")
    public ResponseEntity<?> deleteByModuleId(HttpServletRequest request, @PathVariable Integer moduleId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            moduleService.deleteByModuleIdWithJwtCheck(jwt, moduleId);
            return "Module was deleted successfully";
        });
    }

    @PostMapping("")
    public ResponseEntity<?> save(HttpServletRequest request, @RequestBody ModuleSaveDto dto) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            return moduleService.saveToJwtUser(jwt, dto);
        });
    }

    @GetMapping("/details/{moduleId}")
    public ResponseEntity<?> getDetailsByModuleId(HttpServletRequest request, @PathVariable Integer moduleId) {
        return wrapGet(() -> {
            ModuleDetailsDto module = moduleService.getDetailsDtoByModuleId(moduleId);
            if (module.getAccess().equalsIgnoreCase(Access.PRIVATE.toString())) {
                String jwt = new HttpRequestHeaders(request).getJwtToken();
                if (checker.belongTo(jwt, module.getOwner().getUserId())) {
                    return module;
                } else {
                    throw new IllegalStateException("Your cannot see this module");
                }
            } else {
                return module;
            }
        });
    }

    @GetMapping("/general-details/{id}")
    public ResponseEntity<?> getGeneralDetailsByModuleId(@PathVariable Integer id) {
        return wrapGet(() -> moduleService.getGeneralDetailsDtoByModuleId(id));
    }

}
