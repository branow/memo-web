package com.branow.memoweb.service;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;

public interface FormattedTextService {
    FormattedTextGeneralDetailsDto getFormattedTextGeneralDetailsDtoByTextId(Integer frontSide);
}
