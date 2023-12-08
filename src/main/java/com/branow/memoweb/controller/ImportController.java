package com.branow.memoweb.controller;

import com.branow.memoweb.service.ImportService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapPost;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/import")
@RestController
public class ImportController {

    private final ImportService service;
    private final JwtBelongingChecker belongingChecker;


    @PostMapping("/flashcard/{flashcard-id}/{target-collection-id}")
    public ResponseEntity<?> importFlashcard(HttpServletRequest request,
                                             @PathVariable("flashcard-id") Integer flashcardId,
                                             @PathVariable("target-collection-id") Integer collectionId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwtToken();
            belongingChecker.collectionBelongToOrThrow(jwt, collectionId);
            if (belongingChecker.flashcardBelongTo(jwt, flashcardId)) {
                throw new IllegalStateException("User cannot import own flashcard: " + flashcardId);
            }
            service.importFlashcard(flashcardId, collectionId);
            return "Import was done successfully";
        });
    }

}
