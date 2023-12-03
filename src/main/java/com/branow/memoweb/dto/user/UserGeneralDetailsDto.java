package com.branow.memoweb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGeneralDetailsDto {

    private Integer userId;
    private String username;
    private String description;
    private List<Integer> moduleIds;

}

