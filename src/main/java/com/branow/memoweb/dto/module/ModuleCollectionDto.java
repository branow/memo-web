package com.branow.memoweb.dto.module;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleCollectionDto {

    private Integer moduleId;
    private String moduleName;
    private List<CollectionShortDetailsDto> collections;

}
