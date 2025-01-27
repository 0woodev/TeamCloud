package com.woodev.teamcloud.prompt.gpt.controller;

import com.woodev.teamcloud.config.RestTemplateForGPT;
import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import com.woodev.teamcloud.prompt.gpt.domain.GPTMessage;
import com.woodev.teamcloud.prompt.gpt.domain.LatestPrompt;
import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import com.woodev.teamcloud.prompt.gpt.dto.request.AskGPTRequestDTO;
import com.woodev.teamcloud.prompt.gpt.dto.request.GPTAPIRequestDTO;
import com.woodev.teamcloud.prompt.gpt.dto.request.SaveGPTAPIKeyRequestDTO;
import com.woodev.teamcloud.prompt.gpt.dto.request.SavePromptRequestDTO;
import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import com.woodev.teamcloud.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GPTController {
    private final GPTService gptService;
    private final RestTemplateForGPT restTemplateForGPT;

    @GetMapping("/prompt/{id}")
    public VersioningPrompt getPromptById(@PathVariable("id") UUID promptId) {
        return gptService.getPromptById(promptId);
    }

    @PostMapping("/prompt")
    public VersioningPrompt savePrompt(@RequestBody SavePromptRequestDTO request) {
        return gptService.savePrompt(request);
    }

    @GetMapping("/prompt")
    public List<VersioningPrompt> search(
            @RequestParam(required = false) String promptName
    ) {
        if (promptName != null) {
            return gptService.getAllPromptsByPromptName(promptName);
        }
        return gptService.getAllPrompts();
    }

    @GetMapping("/apikey")
    public GPTAPIKey getApiKey() {
        return gptService.getApiKey();
    }

    @PostMapping("/apikey")
    public GPTAPIKey saveGPTAPIKey(@RequestBody SaveGPTAPIKeyRequestDTO request) {
        return gptService.saveGPTAPIKey(request);
    }

    @PostMapping("/req")
    public String askGPT(
            @RequestBody AskGPTRequestDTO request
        ) {
        // Make requestID(UUID) and save it to the dynamoDB

        // Get API Key from dynamoDB
        String apiKey = gptService.getApiKey().getKey();

        // Get Prompt from dynamoDB
        VersioningPrompt prompt;
        if (request.promptVersion() == null) {
            prompt = gptService.getLatestPrompt(request.promptName());
        } else {
            prompt = gptService.getPromptByPromptNameAndVersion(request.promptName(), request.promptVersion());
        }

        // Call GPT API with the request and API Key
        // TODO GPT API 는 응답을 대기하지 않고, 메세지큐에서 응답을 받아오도록 수정해야함
        // 1. Mapping Prompt and requestBody
        List<GPTMessage> messages = prompt.getMessages();
        for (int i = 0; i < messages.size(); i++) {
            GPTMessage message = messages.get(i);
            // mapping request.data and message
            for (String key : request.data().keySet()) {
                String mappedContent = StringUtils.formatString(message.getContent(), request.data());
                message.setContent(mappedContent);
            }
        }

        // 2. Call GPT API
        /**
         * curl https://api.openai.com/v1/chat/completions \
         *   -H "Content-Type: application/json" \
         *   -H "Authorization: Bearer 여기도 바꾸고
         *   -d '{
         *     "model": "gpt-4o-mini",
         *     "store": true,
         *     "messages": [
         *       여기만 바꾸면 됨
         *     ]
         *   }'
         */
        // 2.1. Make request body
        GPTAPIRequestDTO requestBody = new GPTAPIRequestDTO("gpt-4o-mini", true, messages);

        // 2.2. Call GPT API
        String response = restTemplateForGPT.restTemplate.postForObject("/v1/chat/completions", requestBody, String.class);

        // 3. Save the response to the dynamoDB
        // return UUID;
        return response;
    }

    @GetMapping("/res/{requestId}")
    public String getGPTResponse(@PathVariable("requestId") String requestId) {
        // Get the response from dynamoDB

        // return response;
        return null;
    }
}
