package com.branow.memoweb.dto.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleSaveDto {

    private Integer moduleId;
    private String moduleName;
    private String description;
    private String access;

}
