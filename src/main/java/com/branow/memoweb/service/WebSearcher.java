package com.branow.memoweb.service;

import java.util.List;

import com.branow.memoweb.dto.websearch.EnglishBuiltWord;
import com.branow.memoweb.websearch.WebContainer;
import com.branow.memoweb.websearch.search.items.EnglishWordSenseUnit;

public interface WebSearcher {

    List<WebContainer<String>> searchImages(String phrase);

    List<WebContainer<String>> searchAudios(String phrase);

    List<WebContainer<EnglishWordSenseUnit>> searchEnglishWordSenses(String phrase);

    EnglishBuiltWord searchEnglishWord(String phrase);

}
