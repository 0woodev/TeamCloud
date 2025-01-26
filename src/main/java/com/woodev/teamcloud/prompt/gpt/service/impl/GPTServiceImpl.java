package com.woodev.teamcloud.prompt.gpt.service.impl;

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.repository.GPTAPIKeyRepository;
import com.woodev.teamcloud.prompt.gpt.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.request.SavePromptRequestDTO;
import com.woodev.teamcloud.prompt.gpt.repository.PromptRepository;
import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GPTServiceImpl implements GPTService {

    private final PromptRepository promptRepository;
    private final GPTAPIKeyRepository gptAPIKeyRepository;

    @Override
    public VersioningPrompt savePrompt(SavePromptRequestDTO request) {
        VersioningPrompt versioningPrompt = request.toVersioningPrompt();
        return promptRepository.save(versioningPrompt);
    }

    @Override
    public VersioningPrompt getPromptById(UUID promptId) {
        return promptRepository.findById(promptId).orElse(null);
    }

    @Override
    public List<VersioningPrompt> getPrompts() {
        return (List<VersioningPrompt>) promptRepository.findAll();
    }

    @Override
    public List<VersioningPrompt> getPromptsByPromptName(String promptName) {
        return promptRepository.findAllByPromptName(promptName);
    }

    @Override
    public String deletePromptById(String promptId) {
        return null;
    }

    @Override
    public String updatePrompt(VersioningPrompt latestPrompt) {
        return null;
    }

    @Override
    public GPTAPIKey getApiKey() {
        // TODO : entityName을 Enum으로 관리하도록 수정 && 예외처리
        List<GPTAPIKey> all = gptAPIKeyRepository.findAllByEntityName("GPT_API_KEY");
        return all.getFirst();
    }

    @Override
    public GPTAPIKey saveGPTAPIKey(SaveGPTAPIKeyRequestDTO request) {
        GPTAPIKey apiKey = request.toGPTAPIKey();
        return gptAPIKeyRepository.save(apiKey);
    }
}
