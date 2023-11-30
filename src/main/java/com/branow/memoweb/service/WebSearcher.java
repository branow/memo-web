package com.branow.memoweb.service;

import com.branow.memowebsearch.WebContainer;

import java.util.List;

public interface WebSearcher {

    List<WebContainer<String>> searchImages(String phrase);

    List<WebContainer<String>> searchAudios(String phrase);

}
