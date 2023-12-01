package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.search.EnglishBuiltWord;
import com.branow.memoweb.mapper.SearchMapper;
import com.branow.memoweb.service.WebSearcher;
import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.search.OxfordLearnersDictionariesSearcher;
import com.branow.memowebsearch.search.VeectezySearcher;
import com.branow.memowebsearch.search.items.EnglishSolidWord;
import com.branow.memowebsearch.search.items.EnglishWordSenseUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSearcherImpl implements WebSearcher {

    private final SearchMapper mapper;

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







