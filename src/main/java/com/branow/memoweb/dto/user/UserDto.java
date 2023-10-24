package com.branow.memoweb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Integer userId;
    private String username;
    private String email;
    private String description;
    private Boolean enable;
}
