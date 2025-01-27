package com.woodev.teamcloud.config;

import com.woodev.teamcloud.prompt.gpt.service.GPTService;
import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateForGPT {
    private final GPTService gptService;

    public final RestTemplate restTemplate;

    public RestTemplateForGPT(GPTService gptService) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        this.restTemplate = builder
                .rootUri("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + gptService.getApiKey().getKey())
                .build();

        this.gptService = gptService;
    }
}
