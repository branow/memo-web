package com.branow.memoweb.model.auxilary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextFormat {

    enum Format {
        BOLD, ITALIC, CROSS, UNDERLINED, YELLOW, RED, BLUE, BLACK, GREEN;
    }

    private List<StringFormat> formats;

}
