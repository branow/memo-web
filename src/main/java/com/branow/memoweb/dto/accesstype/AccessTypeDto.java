package com.branow.memoweb.dto.accesstype;

import com.branow.memoweb.model.auxilary.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessTypeDto {

    private Integer accessTypeId;
    private Access access;

}
