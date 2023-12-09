package com.branow.memoweb.controller;

import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.service.FlashcardService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/flashcard")
@RestController
public class FlashcardController {

    private final FlashcardService flashcardService;
    private final JwtBelongingChecker belongingChecker;

    @DeleteMapping("/{flashcardId}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer flashcardId) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            flashcardService.deleteByFlashcardIdWithJwtCheck(jwt, flashcardId);
            return "Flashcard was deleted successfully";
        });
    }

    @PostMapping("/{collectionId}")
    public ResponseEntity<?> save(HttpServletRequest request, @PathVariable Integer collectionId, @RequestBody FlashcardSaveDto dto) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return flashcardService.saveByCollectionIdWithJwtCheck(jwt, collectionId, dto);
        });
    }

    @GetMapping("/details/{flashcardId}")
    public ResponseEntity<?> getDetailsByFlashcardId(@PathVariable Integer flashcardId) {
        return wrapGet(() -> flashcardService.getDetailsDtoByFlashcardId(flashcardId));
    }

    @GetMapping("/learn-context/{flashcardId}/{studyTypeId}")
    public ResponseEntity<?> getLearnContextByFlashcardIdAndStudyTypeId(
                                                    HttpServletRequest request,
                                                    @PathVariable("flashcardId") Integer flashcardId,
                                                    @PathVariable("studyTypeId") Integer studyTypeId) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            belongingChecker.flashcardBelongToOrThrow(jwt, flashcardId);
            return flashcardService.getLearnContextDtoByFlashcardIdAndStudyTypeId(flashcardId, studyTypeId);
        });
    }

}
