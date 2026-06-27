package com.sfg.sfgaiimage.controllers;

import com.sfg.sfgaiimage.model.Question;
import com.sfg.sfgaiimage.services.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/image-api")
@RequiredArgsConstructor
public class ImageController {

    private final OpenAiService openAiService;

    @PostMapping(path = "/generate")
    public String generateImage(@RequestBody Question question) {
        //TODO
        return "";
    }

}
