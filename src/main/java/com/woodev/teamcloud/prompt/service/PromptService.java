package com.woodev.teamcloud.prompt.service;


import com.woodev.teamcloud.prompt.domain.Prompt;

import java.util.List;
import java.util.UUID;

public interface PromptService {
    Prompt savePrompt(String teamName);
    Prompt getPromptById(UUID teamId);

    List<Prompt> getPrompts();

    List<Prompt> getPromptsByName(String teamName);

    String deletePromptById(String teamId);
    String updatePrompt(Prompt team);
}
