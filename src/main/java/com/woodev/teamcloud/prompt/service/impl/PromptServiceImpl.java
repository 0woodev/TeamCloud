package com.woodev.teamcloud.prompt.service.impl;

import com.woodev.teamcloud.prompt.domain.Prompt;
import com.woodev.teamcloud.prompt.repository.PromptRepository;
import com.woodev.teamcloud.prompt.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final PromptRepository promptRepository;
    @Override
    public Prompt savePrompt(String promptName) {
        Prompt prompt = Prompt.builder().name(promptName).build();
        return promptRepository.save(prompt);
    }

    @Override
    public Prompt getPromptById(UUID promptId) {
        return promptRepository.findById(promptId).orElse(null);
    }


    @Override
    public List<Prompt> getPrompts() {
        return (List<Prompt>) promptRepository.findAll();
    }

    @Override
    public List<Prompt> getPromptsByName(String promptName) {
        return promptRepository.findByName(promptName);
    }

    @Override
    public String deletePromptById(String promptId) {
        return null;
    }

    @Override
    public String updatePrompt(Prompt prompt) {
        return null;
    }
}
