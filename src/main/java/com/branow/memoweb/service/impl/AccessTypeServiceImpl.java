package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.model.AccessType;
import com.branow.memoweb.model.auxilary.Access;
import com.branow.memoweb.repository.AccessTypeRepository;
import com.branow.memoweb.service.AccessTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTypeServiceImpl implements AccessTypeService {

    private final AccessTypeRepository repository;


    @Override
    public AccessType getByAccessName(String accessName) {
        Access access = Access.valueOf(accessName);
        return repository.findByAccess(access)
                .orElseThrow(() -> new EntityNotFoundException(AccessType.class, "access", access));
    }

}
