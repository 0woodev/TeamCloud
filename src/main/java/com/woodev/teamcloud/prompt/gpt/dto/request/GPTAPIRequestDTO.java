package com.woodev.teamcloud.prompt.gpt.dto.request;

import com.woodev.teamcloud.prompt.gpt.domain.GPTMessage;
import lombok.*;

import java.util.List;

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

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GPTAPIRequestDTO {
    private String model;
    private boolean store;
    private List<GPTMessage> messages;
}
