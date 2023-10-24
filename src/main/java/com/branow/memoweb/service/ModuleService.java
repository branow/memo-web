package com.branow.memoweb.service;

import com.branow.memoweb.dto.module.ModuleSimpleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {

    ModuleSimpleDto getSimpleDtoById(Integer id);

    List<Integer> getIdAllByUserId(Integer userId);

    List<Integer> getIdAllByUserIdWithPublicAccess(Integer userId);

}
