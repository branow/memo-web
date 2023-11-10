package com.branow.memoweb.mapper;

import com.branow.memoweb.exception.FileTypeNotFound;
import com.branow.memoweb.model.auxilary.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileFormatMapper {

    FileType toFileType(FileType.Format format) {
        for (FileType type: FileType.values()) {
            for (FileType.Format f: type.getFormats()) {
                if (f == format) {
                    return type;
                }
            }
        }
        throw new FileTypeNotFound(format);
    }

}
