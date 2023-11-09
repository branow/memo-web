package com.branow.memoweb.controller;

import com.branow.memoweb.service.FlashcardService;
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


    @GetMapping("details/{flashcardId}")
    public ResponseEntity<?> getDetailsByFlashcardId(@PathVariable Integer flashcardId) {
        return wrapGet(() -> flashcardService.getDetailsDtoByFlashcardId(flashcardId));
    }

}
