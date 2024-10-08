package com.branow.memoweb.websearch.search;

import java.util.List;

import com.branow.memoweb.websearch.WebContainer;
import com.branow.memoweb.websearch.parser.VeectezyParser;

public class VeectezySearcher extends DataSearcher {

    private final String SEARCH_IMAGE_FREE_AI_PAGE =
            "/search?qterm=[query]&content_type=image&license-free=true&ai_generated-ai_generated=true&page=[page]";

    public VeectezySearcher() {
        super("www.vecteezy.com");
    }

    public String searchImagesUrl(String phrase) {
        String query = phrase.replaceAll(" ", "-");
        String url = url() +  SEARCH_IMAGE_FREE_AI_PAGE.replace("[query]", query).replace("[page]", String.valueOf(1));
        List<String> images = new VeectezyParser(get(url)).getImageUrls();
        if (images.isEmpty()) {
            return null;
        } else {
            return images.get(0);
        }
    }

    public List<WebContainer<String>> searchImagesUrlFreePageFirst(String phrase) {
        String query = phrase.replaceAll(" ", "-");
        String url = url() +  SEARCH_IMAGE_FREE_AI_PAGE.replace("[query]", query).replace("[page]", String.valueOf(1));
        return new VeectezyParser(get(url)).getImageUrls().stream()
                .map(e -> {
                    String[] sub = e.split("/");
                    String name = sub[sub.length - 1].split("\\.")[0];
                    return new WebContainer<>(domain, name, e);
                }).toList();
    }

}
