package com.sfg.sfgaiimage.services;

import com.sfg.sfgaiimage.model.Question;

public interface OpenAiService {

    void generateImage(Question question);
}
