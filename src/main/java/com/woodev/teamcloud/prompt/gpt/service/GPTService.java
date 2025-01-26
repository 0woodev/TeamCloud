package com.woodev.teamcloud.prompt.gpt.service;


import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.request.SavePromptRequestDTO;

import java.util.List;
import java.util.UUID;

public interface GPTService {
    VersioningPrompt savePrompt(SavePromptRequestDTO request);
    VersioningPrompt getPromptById(UUID teamId);

    List<VersioningPrompt> getPrompts();

    List<VersioningPrompt> getPromptsByPromptName(String teamName);

    String deletePromptById(String teamId);
    String updatePrompt(VersioningPrompt team);

    GPTAPIKey getApiKey();

    GPTAPIKey saveGPTAPIKey(SaveGPTAPIKeyRequestDTO request);
}
