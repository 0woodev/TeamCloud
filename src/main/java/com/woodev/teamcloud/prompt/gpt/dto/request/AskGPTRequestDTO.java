package com.woodev.teamcloud.prompt.gpt.dto.request;

import java.util.Map;

public record AskGPTRequestDTO(
        String promptName,
        String promptVersion,
        Map<String, Object> data
) {
}
