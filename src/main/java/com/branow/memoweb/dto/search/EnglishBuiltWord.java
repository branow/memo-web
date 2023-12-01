package com.branow.memoweb.dto.search;

import com.branow.memowebsearch.search.items.EnglishPartWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
