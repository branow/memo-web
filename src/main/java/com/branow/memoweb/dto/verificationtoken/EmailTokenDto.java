package com.branow.memoweb.dto.verificationtoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailTokenDto {

    private String email;
    private String token;

}
