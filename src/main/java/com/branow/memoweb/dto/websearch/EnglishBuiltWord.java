package com.branow.memoweb.dto.websearch;

import java.util.List;

import com.branow.memoweb.websearch.search.items.EnglishPartWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnglishBuiltWord {

    private String word;
    private String audio;
    private String image;
    private String transcription;
    private List<EnglishPartWord> partWordList;

}
