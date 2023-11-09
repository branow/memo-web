package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.JwtIllegalSubjectException;
import com.branow.memoweb.exception.entitynotfound.ModuleNotFoundException;
import com.branow.memoweb.repository.ModuleRepository;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtBelongingCheckerImpl implements JwtBelongingChecker {

    private final JwtService jwtService;
    private final ModuleRepository moduleRepository;


    @Override
    public void belongToOrThrow(String jwt, Integer userId) {
        if (!belongTo(jwt, userId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given user id");
        }
    }

    @Override
    public void moduleBelongToOrThrow(String jwt, Integer moduleId) {
        if (!moduleBelongTo(jwt, moduleId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given module id");
        }
    }

    @Override
    public boolean belongTo(String jwt, Integer userId) {
        return jwtService.hasSubject(jwt, userId.toString());
    }

    @Override
    public boolean moduleBelongTo(String jwt, Integer moduleId) {
        return belongTo(jwt, moduleRepository.findUserByModuleId(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException("id", moduleId)));
    }

    @Override
    public Integer getUserId(String jwt) {
        try {
            return Integer.parseInt(jwtService.getSubject(jwt));
        } catch (NumberFormatException e) {
            throw new JwtIllegalSubjectException("Jwt subject is not a User Id", e);
        }
    }

}
