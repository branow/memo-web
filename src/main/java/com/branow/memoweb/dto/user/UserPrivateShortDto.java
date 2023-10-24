package com.branow.memoweb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrivateShortDto {

    private Integer userId;
    private String username;
    private String email;
    private Boolean enable;

}
