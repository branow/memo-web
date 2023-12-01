package com.branow.memoweb.controller;

import com.branow.memoweb.service.WebSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/search")
@RestController
public class SearchController {

    private final WebSearcher searcher;


    @GetMapping("/images/{phrase}")
    public ResponseEntity<?> images(@PathVariable String phrase) {
        return wrapGet(() -> searcher.searchImages(phrase));
    }

    @GetMapping("/audios/{phrase}")
    public ResponseEntity<?> audios(@PathVariable String phrase) {
        return wrapGet(() -> searcher.searchAudios(phrase));
    }

    @GetMapping("/english-word-senses/{phrase}")
    public ResponseEntity<?> englishWordSenses(@PathVariable String phrase) {
        return wrapGet(() -> searcher.searchEnglishWordSenses(phrase));
    }

    @GetMapping("/english-word/{phrase}")
    public ResponseEntity<?> englishWords(@PathVariable String phrase) {
        return wrapGet(() -> searcher.searchEnglishWord(phrase));
    }

}
