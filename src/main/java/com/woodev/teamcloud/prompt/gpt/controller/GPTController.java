package com.woodev.teamcloud.prompt.gpt.controller;

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.request.SavePromptRequestDTO;
import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/req")
    public String requestPrompt(
            @PathVariable("id") String promptName,
            @RequestBody Map<String, Object> request
        ) {
        // Make requestID(UUID) and save it to the dynamoDB

        // Get API Key from dynamoDB
        // Get Prompt from dynamoDB


        // Call GPT API with the request and API Key
        // TODO GPT API 는 응답을 대기하지 않고, 메세지큐에서 응답을 받아오도록 수정해야함
        // 1. Mapping Prompt and requestBody
        // 2. Call GPT API
        // 3. Save the response to the dynamoDB

        // return UUID;
        return null;
    }

    @GetMapping("/res/{requestId}")
    public String getGPTResponse(@PathVariable("requestId") String requestId) {
        // Get the response from dynamoDB

        // return response;
        return null;
    }
}
