package com.woodev.teamcloud.prompt.gpt.dto.request;


// PromptController.savePrompt() 의 request body dto 를 정의합니다.

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIOptions;
import com.woodev.teamcloud.prompt.gpt.domain.GPTMessage;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;

import java.util.List;

public record SavePromptRequestDTO(
        String promptName,
        String description,
        String aiModel,
        String aiModelVersion,
        GPTAPIOptions params,
        List<GPTMessage> messages
) {
    public VersioningPrompt toVersioningPrompt() {
        return VersioningPrompt.builder()
                .promptName(promptName)
                .aiModel(aiModel)
                .aiModelVersion(aiModelVersion)
                .params(params)
                .description(description)
                .messages(messages)
                .build();
    }
}