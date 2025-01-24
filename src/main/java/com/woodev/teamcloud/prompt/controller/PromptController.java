package com.woodev.teamcloud.prompt.controller;

import com.woodev.teamcloud.prompt.domain.Prompt;
import com.woodev.teamcloud.prompt.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prompt")
@RequiredArgsConstructor
public class PromptController {
    private final PromptService promptService;

    @GetMapping("/{id}")
    public Prompt getPromptById(@PathVariable("id") UUID teamId) {
        return promptService.getPromptById(teamId);
    }

    @PostMapping("")
    public Prompt savePrompt(@RequestBody String teamName) {
        return promptService.savePrompt(teamName);
    }

    @GetMapping("")
    public List<Prompt> search(
            @RequestParam(required = false) String teamName
    ) {
        if (teamName != null) {
            return promptService.getPromptsByName(teamName);
        }
        return promptService.getPrompts();
    }
}
