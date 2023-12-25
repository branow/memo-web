package com.branow.memoweb.controller;

import com.branow.memoweb.dto.collection.CollectionSaveDto;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.util.HttpRequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@RequestMapping("/collection")
@RestController
public class CollectionController {

    private final CollectionService collectionService;


    @DeleteMapping("/{collectionId}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable Integer collectionId) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            collectionService.deleteByCollectionIdWithJwtCheck(jwt, collectionId);
            return "Collection was deleted successfully";
        });
    }

    @PostMapping("/{moduleId}")
    public ResponseEntity<?> save(HttpServletRequest request, @PathVariable Integer moduleId, @RequestBody CollectionSaveDto dto) {
        return wrapGet(() -> {
            String jwt = new HttpRequestHeaders(request).getJwt();
            return collectionService.saveByModuleIdWithJwtCheck(jwt, moduleId, dto);
        });
    }

    @GetMapping("details/{collectionId}")
    public ResponseEntity<?> getDetailsByCollectionId(@PathVariable Integer collectionId) {
        return wrapGet(() -> collectionService.getDetailsDtoByCollectionId(collectionId));
    }

}
