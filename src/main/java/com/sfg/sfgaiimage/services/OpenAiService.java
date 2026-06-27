package com.sfg.sfgaiimage.services;

import com.sfg.sfgaiimage.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAiService {

    byte[] generateImage(Question question);

    String recognize(MultipartFile file);
}
