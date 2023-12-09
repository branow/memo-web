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


    @PostMapping("/module/{module-id}")
    public ResponseEntity<?> importCollection(HttpServletRequest request,
                                              @PathVariable("module-id") Integer moduleId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            Integer targetUserId = belongingChecker.getUserId(jwt);
            if (belongingChecker.moduleBelongTo(jwt, moduleId)) {
                throw new IllegalStateException("User cannot import own module: " + moduleId);
            }
            service.importModule(moduleId, targetUserId);
            return "Module was imported successfully";
        });
    }

    @PostMapping("/collection/{collection-id}/{target-module-id}")
    public ResponseEntity<?> importCollection(HttpServletRequest request,
                                             @PathVariable("collection-id") Integer collectionId,
                                             @PathVariable("target-module-id") Integer targetModuleId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            belongingChecker.moduleBelongToOrThrow(jwt, targetModuleId);
            if (belongingChecker.collectionBelongTo(jwt, collectionId)) {
                throw new IllegalStateException("User cannot import own collection: " + collectionId);
            }
            service.importCollection(collectionId, targetModuleId);
            return "Collection was imported successfully";
        });
    }

    @PostMapping("/flashcard/{flashcard-id}/{target-collection-id}")
    public ResponseEntity<?> importFlashcard(HttpServletRequest request,
                                             @PathVariable("flashcard-id") Integer flashcardId,
                                             @PathVariable("target-collection-id") Integer collectionId) {
        return wrapPost(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            belongingChecker.collectionBelongToOrThrow(jwt, collectionId);
            if (belongingChecker.flashcardBelongTo(jwt, flashcardId)) {
                throw new IllegalStateException("User cannot import own flashcard: " + flashcardId);
            }
            service.importFlashcard(flashcardId, collectionId);
            return "Flashcard was imported successfully";
        });
    }

}
