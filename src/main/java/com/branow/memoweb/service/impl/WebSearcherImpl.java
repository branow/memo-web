package com.branow.memoweb.service.impl;

import com.branow.memoweb.service.WebSearcher;
import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.search.OxfordLearnersDictionariesSearcher;
import com.branow.memowebsearch.search.VeectezySearcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebSearcherImpl implements WebSearcher {

    @Override
    public List<WebContainer<String>> searchImages(String phrase) {
        return new VeectezySearcher().getImagesUrlFreePageFirst(phrase);
    }

    @Override
    public List<WebContainer<String>> searchAudios(String phrase) {
        return new OxfordLearnersDictionariesSearcher().searchAudiosUrls(phrase);
    }

}
