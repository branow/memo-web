package com.branow.memoweb.controller;

import com.branow.memoweb.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.branow.memoweb.controller.response.ResponseWrapper.wrapGet;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {

    private final MediaService mediaService;


    @GetMapping(value = "/image/{mediaId}", produces = "image/jpeg")
    public ResponseEntity<?> getImageByMediaId(@PathVariable Integer mediaId) {
        return wrapGet(() -> mediaService.getByMediaId(mediaId).getMedia());
    }

    @GetMapping(value = "/audio/{mediaId}", produces = "audio/mpeg")
    public ResponseEntity<?> getAudioByMediaId(@PathVariable Integer mediaId) {
        return wrapGet(() -> mediaService.getByMediaId(mediaId).getMedia());
    }

}
