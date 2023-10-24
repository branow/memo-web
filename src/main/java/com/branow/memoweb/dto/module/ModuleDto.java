package com.branow.memoweb.dto.module;

import com.branow.memoweb.dto.accesstype.AccessTypeDto;
import com.branow.memoweb.dto.collection.CollectionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleDto {

    private Integer moduleId;
    private String moduleName;
    private String description;
    private AccessTypeDto accessType;
    private List<CollectionDto> collections;

}
