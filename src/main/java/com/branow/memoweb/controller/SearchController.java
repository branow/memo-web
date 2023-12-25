package com.branow.memoweb.controller;

import com.branow.memoweb.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchController {

    private final SearchService service;

    @GetMapping("/user/{query}/{page-number}")
    public ResponseEntity<?> searchUserAllByQuery(@PathVariable("query") String query,
                                                  @PathVariable("page-number") Integer pageNumber) {
        return wrapGet(() -> {
            String preparedQuery = query.replaceAll("-", " ");
            return service.searchUserAllByQuery(preparedQuery, pageNumber);
        });
    }

    @GetMapping("/module/{query}/{page-number}")
    public ResponseEntity<?> searchModuleAllByQuery(@PathVariable("query") String query,
                                                  @PathVariable("page-number") Integer pageNumber) {
        return wrapGet(() -> {
            String preparedQuery = query.replaceAll("-", " ");
            return service.searchModuleAllByQuery(preparedQuery, pageNumber);
        });
    }

}
