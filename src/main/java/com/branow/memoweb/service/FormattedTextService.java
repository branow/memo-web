package com.branow.memoweb.service;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.model.FormattedText;

public interface FormattedTextService {

    FormattedText getByTextId(Integer textId);

    FormattedTextGeneralDetailsDto getGeneralDetailsDtoByTextId(Integer textId);

    FormattedText createEmpty();

    FormattedTextSaveDto saveWithJwtCheck(String jwt, FormattedTextSaveDto dto);
}
