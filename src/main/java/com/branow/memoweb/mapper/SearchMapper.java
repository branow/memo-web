package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.search.EnglishBuiltWord;
import com.branow.memowebsearch.search.items.EnglishSolidWord;
import org.springframework.stereotype.Service;

@Service
public class SearchMapper {

    public EnglishBuiltWord toEnglishBuiltWord(EnglishSolidWord solidWord, String image) {
        return EnglishBuiltWord.builder()
                .partWordList(solidWord.getPartWordList())
                .word(solidWord.getWord())
                .audio(solidWord.getAudio())
                .image(image)
                .transcription(solidWord.getTranscription())
                .audio(solidWord.getAudio())
                .build();
    }

}
