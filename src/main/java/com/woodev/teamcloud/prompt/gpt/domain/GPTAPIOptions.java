package com.woodev.teamcloud.prompt.gpt.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class GPTAPIOptions {

    @DynamoDBAttribute
    private Integer maxTokens;

    @DynamoDBAttribute
    private Double temperature;
}
