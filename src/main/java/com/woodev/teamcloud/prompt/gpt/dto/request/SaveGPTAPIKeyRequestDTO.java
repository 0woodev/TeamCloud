package com.woodev.teamcloud.prompt.gpt.dto.request;


// PromptController.savePrompt() 의 request body dto 를 정의합니다.

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;

public record SaveGPTAPIKeyRequestDTO(
        String key
) {
    public GPTAPIKey toGPTAPIKey() {
        return GPTAPIKey.builder()
                .key(key)
                .build();
    }
}