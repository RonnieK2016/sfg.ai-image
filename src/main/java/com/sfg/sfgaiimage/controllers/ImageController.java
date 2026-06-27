package com.sfg.sfgaiimage.controllers;

import com.sfg.sfgaiimage.model.Question;
import com.sfg.sfgaiimage.services.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/image-api")
@RequiredArgsConstructor
public class ImageController {

    private final OpenAiService openAiService;

    @PostMapping(value = "/recognize", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> recognize(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
    ) throws IOException {

        return ResponseEntity.ok(openAiService.recognize(file));
    }

    @PostMapping(path = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateImage(@RequestBody Question question) {
        return openAiService.generateImage(question);
    }

}
