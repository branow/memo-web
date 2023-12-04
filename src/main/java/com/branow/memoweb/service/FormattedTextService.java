package com.branow.memoweb.service;

import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.model.FormattedText;

public interface FormattedTextService {

    FormattedText getByTextId(Integer textId);

    FormattedTextDetailsDto getDetailsDtoByTextId(Integer textId);

    FormattedText save(FormattedTextSaveDto dto);
}
