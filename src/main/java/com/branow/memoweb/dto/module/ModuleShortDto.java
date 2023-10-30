package com.branow.memoweb.dto.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleShortDto {

    private Integer moduleId;
    private String moduleName;
    private String shortDescription;
    private String access;

}
