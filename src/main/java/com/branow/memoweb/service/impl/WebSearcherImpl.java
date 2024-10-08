package com.branow.memoweb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.websearch.EnglishBuiltWord;
import com.branow.memoweb.mapper.WebSearchMapper;
import com.branow.memoweb.service.WebSearcher;
import com.branow.memoweb.websearch.WebContainer;
import com.branow.memoweb.websearch.search.OxfordLearnersDictionariesSearcher;
import com.branow.memoweb.websearch.search.VeectezySearcher;
import com.branow.memoweb.websearch.search.items.EnglishSolidWord;
import com.branow.memoweb.websearch.search.items.EnglishWordSenseUnit;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSearcherImpl implements WebSearcher {

    private final WebSearchMapper mapper;

    @Override
    public List<WebContainer<String>> searchImages(String phrase) {
        return new VeectezySearcher().searchImagesUrlFreePageFirst(phrase);
    }

    @Override
    public List<WebContainer<String>> searchAudios(String phrase) {
        return new OxfordLearnersDictionariesSearcher().searchAudiosUrls(phrase);
    }

    @Override
    public List<WebContainer<EnglishWordSenseUnit>> searchEnglishWordSenses(String phrase) {
        return new OxfordLearnersDictionariesSearcher().searchEnglishWordSenseUnits(phrase);
    }

    @Override
    public EnglishBuiltWord searchEnglishWord(String phrase) {
        EnglishSolidWord solidWord = new OxfordLearnersDictionariesSearcher().searchEnglishSolidWord(phrase);
        String image = new VeectezySearcher().searchImagesUrl(phrase);
        return mapper.toEnglishBuiltWord(solidWord, image);
    }

}







