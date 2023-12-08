package com.branow.memoweb.dto.search;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchingDto {

    private Integer userId;
    private String username;
    private String shortDescription;
    private Integer publicModuleNumber;
    private Integer privateModuleNumber;

}
