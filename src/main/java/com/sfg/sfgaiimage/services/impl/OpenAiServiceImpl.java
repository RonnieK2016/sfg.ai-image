package com.sfg.sfgaiimage.services.impl;

import com.sfg.sfgaiimage.model.Question;
import com.sfg.sfgaiimage.services.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService {

    private final ImageModel imageModel;

    private final ChatModel chatModel;

    @Override
    public byte[] generateImage(Question question) {
        var options = OpenAiImageOptions.builder()
                .height(1024).width(1024)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }

    @Override
    public String recognize(MultipartFile file) {
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .build();

        var userMessage = UserMessage.builder()
                .media(new Media(MimeTypeUtils.IMAGE_JPEG, file.getResource()))
                .text("Explain what do you see in this picture?")
                .build();

        ChatResponse response = chatModel.call(new Prompt(List.of(userMessage), options));

        return response.getResult().getOutput().toString();
    }


}
