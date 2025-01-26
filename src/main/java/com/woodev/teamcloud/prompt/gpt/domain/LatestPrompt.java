package com.woodev.teamcloud.prompt.gpt.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "prompt")
public class LatestPrompt extends VersioningPrompt {
    @DynamoDBAttribute
    @Builder.Default
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "promptNamePromptVersionIndex")
    private String promptVersion = "LATEST";

    @DynamoDBAttribute
    private String appliedPromptVersion;

    public static LatestPrompt from(VersioningPrompt versioningPrompt) {
        return LatestPrompt.builder()
                .entityName(versioningPrompt.getEntityName())
                .promptName(versioningPrompt.getPromptName())
                .aiModel(versioningPrompt.getAiModel())
                .aiModelVersion(versioningPrompt.getAiModelVersion())
                .params(versioningPrompt.getParams())
                .description(versioningPrompt.getDescription())
                .messages(versioningPrompt.getMessages())
                .appliedPromptVersion(versioningPrompt.getPromptVersion())
                .build();
    }
}
