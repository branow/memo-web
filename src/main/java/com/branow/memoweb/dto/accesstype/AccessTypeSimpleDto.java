package com.branow.memoweb.dto.accesstype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessTypeSimpleDto {

    private Integer accessTypeId;
    private String access;

}
