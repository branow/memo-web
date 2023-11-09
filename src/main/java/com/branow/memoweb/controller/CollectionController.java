package com.branow.memoweb.controller;

import com.branow.memoweb.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/collection")
@RestController
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping("details/{collectionId}")
    public ResponseEntity<?> getDetailsByCollectionId(@PathVariable Integer collectionId) {
        return wrapGet(() -> collectionService.getDetailsDtoByCollectionId(collectionId));
    }

}
