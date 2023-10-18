package com.branow.memoweb.model.auxilary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StringFormat {

    private int start;
    private int end;
    private TextFormat.Format format;


}
