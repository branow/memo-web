package com.branow.memoweb.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleSearchingDto {

    private Integer moduleId;
    private String moduleName;
    private String shortDescription;
    private Integer collectionNumber;
    private Integer userId;
    private String username;

}
