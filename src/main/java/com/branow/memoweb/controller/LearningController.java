package com.branow.memoweb.controller;

import com.branow.memoweb.service.LearningService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@RequestMapping("/learn")
@RestController
public class LearningController {

    private final LearningService service;

    @GetMapping("/{studyTypeId}")
    public ResponseEntity<?> getFlashcardIdsToLearn(HttpServletRequest request,
                                                    @PathVariable Integer studyTypeId,
                                                    @RequestParam("collections") String collections,
                                                    @RequestParam("sort") String sort,
                                                    @RequestParam("levels") String levels) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            List<Integer> collectionsList = Arrays.stream(collections.split(","))
                    .map(Integer::parseInt).toList();
            boolean sortBool = Boolean.parseBoolean(sort);
            List<Integer> levelsList = Arrays.stream(levels.split(","))
                    .map(Integer::parseInt).toList();
            return service.getFlashcardIdsToLearnWithJwtCheck(jwt, studyTypeId, collectionsList, levelsList, sortBool);
        });
    }

    @PostMapping("/{flashcardId}/{studyTypeId}/{score}")
    public ResponseEntity<?> setScoreToFlashcard(HttpServletRequest request,
                                                 @PathVariable("flashcardId") Integer flashcardId,
                                                 @PathVariable("studyTypeId") Integer studyTypeId,
                                                 @PathVariable("score") Integer score) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return service.setScoreToFlashcardWithJwtCheck(jwt, flashcardId, studyTypeId, score);
        });
    }

}
