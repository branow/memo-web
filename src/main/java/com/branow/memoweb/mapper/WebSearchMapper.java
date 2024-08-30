package com.branow.memoweb.mapper;

import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.websearch.EnglishBuiltWord;
import com.branow.memoweb.websearch.search.items.EnglishSolidWord;

@Service
public class WebSearchMapper {

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
