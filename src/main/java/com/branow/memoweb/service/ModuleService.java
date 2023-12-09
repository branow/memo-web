package com.branow.memoweb.service;

import com.branow.memoweb.dto.module.ModuleCollectionDto;
import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleSaveDto;
import com.branow.memoweb.model.Module;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ModuleService {

    List<ModuleCollectionDto> getCollectionDtoAllByUserId(Integer userId);

    Module getByModuleId(Integer moduleId);

    Module save(Module module);

    ModuleSaveDto saveByUserId(Integer userId, ModuleSaveDto dto);

    ModuleSaveDto saveToJwtUser(String jwtToken, ModuleSaveDto dto);

    ModuleDetailsDto getDetailsDtoByModuleId(Integer id);

    ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id);

    List<Integer> getModuleIdAllByUserId(Integer userId);

    List<Integer> getModuleIdWithPublicAccessAllByUserId(Integer userId);

    List<Module> getAllByUserId(Integer userId);

    void deleteByModuleIdWithJwtCheck(String jwt, Integer moduleId);
}
