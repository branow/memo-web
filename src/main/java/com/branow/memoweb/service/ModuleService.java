package com.branow.memoweb.service;

import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {

    ModuleDetailsDto getDetailsDtoByModuleId(Integer id);

    ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id);

    List<Integer> getIdAllByUserId(Integer userId);

    List<Integer> getIdWithPublicAccessAllByUserId(Integer userId);

}
