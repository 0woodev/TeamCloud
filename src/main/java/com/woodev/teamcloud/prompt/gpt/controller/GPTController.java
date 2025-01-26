package com.woodev.teamcloud.prompt.gpt.controller;

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.request.SavePromptRequestDTO;
import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GPTController {
    private final GPTService GPTService;

    @GetMapping("/prompt/{id}")
    public VersioningPrompt getPromptById(@PathVariable("id") UUID promptId) {
        return GPTService.getPromptById(promptId);
    }

    @PostMapping("/prompt")
    public VersioningPrompt savePrompt(@RequestBody SavePromptRequestDTO request) {
        return GPTService.savePrompt(request);
    }

    @GetMapping("/prompt")
    public List<VersioningPrompt> search(
            @RequestParam(required = false) String promptName
    ) {
        if (promptName != null) {
            return GPTService.getPromptsByPromptName(promptName);
        }
        return GPTService.getPrompts();
    }

    @GetMapping("/apikey")
    public GPTAPIKey getApiKey() {
        return GPTService.getApiKey();
    }

    @PostMapping("/apikey")
    public GPTAPIKey saveGPTAPIKey(@RequestBody SaveGPTAPIKeyRequestDTO request) {
        return GPTService.saveGPTAPIKey(request);
    }
}
