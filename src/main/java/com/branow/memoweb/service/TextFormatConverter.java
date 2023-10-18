package com.branow.memoweb.service;

;
import com.branow.memoweb.model.auxilary.TextFormat;
import jakarta.persistence.AttributeConverter;

public interface TextFormatConverter extends AttributeConverter<TextFormat, String> {


    String toString(TextFormat textFormat);

    TextFormat toTextFormat(String str);

    @Override
    default String convertToDatabaseColumn(TextFormat textFormat) {
        return toString(textFormat);
    }

    @Override
    default TextFormat convertToEntityAttribute(String s) {
        return toTextFormat(s);
    }
}
