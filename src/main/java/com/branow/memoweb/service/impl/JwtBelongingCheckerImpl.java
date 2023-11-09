package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.JwtIllegalSubjectException;
import com.branow.memoweb.exception.entitynotfound.CollectionNotFoundException;
import com.branow.memoweb.exception.entitynotfound.ModuleNotFoundException;
import com.branow.memoweb.repository.CollectionRepository;
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
    private final CollectionRepository collectionRepository;


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
    public void collectionBelongToOrThrow(String jwt, Integer collectionId) {
        if (!collectionBelongTo(jwt, collectionId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given collection id");
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
    public boolean collectionBelongTo(String jwt, Integer collectionId) {
        return moduleBelongTo(jwt, collectionRepository.findModuleByCollectionId(collectionId)
                .orElseThrow(() -> new CollectionNotFoundException("id", collectionId)));
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
