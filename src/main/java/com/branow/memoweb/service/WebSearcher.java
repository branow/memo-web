package com.branow.memoweb.service;

import com.branow.memoweb.dto.search.EnglishBuiltWord;
import com.branow.memowebsearch.WebContainer;
import com.branow.memowebsearch.search.items.EnglishWordSenseUnit;

import java.util.List;

public interface WebSearcher {

    List<WebContainer<String>> searchImages(String phrase);

    List<WebContainer<String>> searchAudios(String phrase);

    List<WebContainer<EnglishWordSenseUnit>> searchEnglishWordSenses(String phrase);

    EnglishBuiltWord searchEnglishWord(String phrase);

}
