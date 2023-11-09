package com.branow.memoweb.service;

import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleSaveDto;
import com.branow.memoweb.model.Module;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {

    ModuleSaveDto saveByUserId(Integer userId, ModuleSaveDto dto);

    ModuleSaveDto saveByJwtToken(String jwtToken, ModuleSaveDto dto);

    ModuleDetailsDto getDetailsDtoByModuleId(Integer id);

    ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id);

    List<Integer> getModuleIdAllByUserId(Integer userId);

    List<Integer> getModuleIdWithPublicAccessAllByUserId(Integer userId);

    List<Module> getAllByUserId(Integer userId);

    void deleteByJwtTokenAndModuleId(String jwt, Integer moduleId);
}
