package com.branow.memoweb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserJwtDto {

    private UserPrivateShortDetailsDto user;
    private String jwt;

}
