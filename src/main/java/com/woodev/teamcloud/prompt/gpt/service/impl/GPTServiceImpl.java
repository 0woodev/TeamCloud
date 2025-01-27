package com.woodev.teamcloud.prompt.gpt.service.impl;

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.LatestPrompt;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.repository.GPTAPIKeyRepository;
import com.woodev.teamcloud.prompt.gpt.dto.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.dto.request.SavePromptRequestDTO;
import com.woodev.teamcloud.prompt.gpt.repository.LatestPromptRepository;
import com.woodev.teamcloud.prompt.gpt.repository.VersioningPromptRepository;
import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GPTServiceImpl implements GPTService {

    private final VersioningPromptRepository versioningPromptRepository;
    private final LatestPromptRepository latestPromptRepository;
    private final GPTAPIKeyRepository gptAPIKeyRepository;

    @Override
    public VersioningPrompt savePrompt(SavePromptRequestDTO request) {
        VersioningPrompt versioningPrompt = request.toVersioningPrompt();
        return versioningPromptRepository.save(versioningPrompt);
    }

    @Override
    public VersioningPrompt getPromptById(UUID promptId) {
        return versioningPromptRepository.findById(promptId).orElse(null);
    }

    @Override
    public List<VersioningPrompt> getAllPrompts() {
        return (List<VersioningPrompt>) versioningPromptRepository.findAll();
    }

    @Override
    public List<VersioningPrompt> getAllPromptsByPromptName(String promptName) {
        return versioningPromptRepository.findAllByPromptName(promptName);
    }

    @Override
    public VersioningPrompt getPromptByPromptNameAndVersion(String promptName, String version) {
        return versioningPromptRepository.findByPromptNameAndPromptVersion(promptName, version);
    }

    @Override
    public LatestPrompt getLatestPrompt(String promptName) {
        return latestPromptRepository.findFirstByPromptNameAndPromptVersion(promptName, "LATEST");
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
