package com.sfg.sfgaiimage.services.impl;

import com.sfg.sfgaiimage.model.Question;
import com.sfg.sfgaiimage.services.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService {

    private final ImageModel imageModel;

    @Override
    public byte[] generateImage(Question question) {
        var options = OpenAiImageOptions.builder()
                .height(1024).width(1024)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }
}
