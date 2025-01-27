package com.woodev.teamcloud.prompt.gpt.service;


import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.LatestPrompt;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.dto.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.dto.request.SavePromptRequestDTO;

import java.util.List;
import java.util.UUID;

public interface GPTService {
    VersioningPrompt savePrompt(SavePromptRequestDTO request);
    VersioningPrompt getPromptById(UUID teamId);

    List<VersioningPrompt> getAllPrompts();

    List<VersioningPrompt> getAllPromptsByPromptName(String teamName);

    VersioningPrompt getPromptByPromptNameAndVersion(String teamName, String version);

    LatestPrompt getLatestPrompt(String promptName);

    String deletePromptById(String teamId);
    String updatePrompt(VersioningPrompt team);

    GPTAPIKey getApiKey();

    GPTAPIKey saveGPTAPIKey(SaveGPTAPIKeyRequestDTO request);
}
