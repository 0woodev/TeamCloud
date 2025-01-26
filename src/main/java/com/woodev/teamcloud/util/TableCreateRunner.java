package com.woodev.teamcloud.util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.woodev.teamcloud.prompt.gpt.domain.LatestPrompt;
import com.woodev.teamcloud.team.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Customer Application에서 사용할 customer 테이블을 자동으로 생성하는 클래스
 * DynamodbDemoApplicaiton 시작할때 자동으로 실행되어 customer 테이블을 생성
 * applicaiton.properties 파일에 있는 dynamodb 정보를 환경에 맞게 수정 필요
 */

@Component
public class TableCreateRunner implements CommandLineRunner {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    void createTable() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest teamTableRequest = dynamoDBMapper
                .generateCreateTableRequest(Team.class);
        CreateTableRequest promptTableRequest = dynamoDBMapper
                .generateCreateTableRequest(LatestPrompt.class);


        teamTableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        promptTableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));


        // GSI 추가
        GlobalSecondaryIndex entityNameIdIndex = new GlobalSecondaryIndex()
                .withIndexName("entityNameIdIndex")
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
                .withKeySchema(
                        new KeySchemaElement("entityName", KeyType.HASH),
                        new KeySchemaElement("id", KeyType.RANGE)
                );
        // GSI 추가
        GlobalSecondaryIndex promptNamePromptVersionIndex = new GlobalSecondaryIndex()
                .withIndexName("promptNamePromptVersionIndex")
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
                .withKeySchema(
                        new KeySchemaElement("promptName", KeyType.HASH),
                        new KeySchemaElement("promptVersion", KeyType.RANGE)
                );

        teamTableRequest.setGlobalSecondaryIndexes(List.of(entityNameIdIndex));
        promptTableRequest.setGlobalSecondaryIndexes(List.of(entityNameIdIndex, promptNamePromptVersionIndex));
//        CreateTableRequest musicCollectionTableRequest = dynamoDBMapper
//                .generateCreateTableRequest(MusicCollection.class);
//        musicCollectionTableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, teamTableRequest);
        TableUtils.createTableIfNotExists(amazonDynamoDB, promptTableRequest);
    }

    void deleteTable() {
        DeleteTableRequest teamTableRequest = dynamoDBMapper.generateDeleteTableRequest(Team.class);
        DeleteTableRequest promptTableRequest = dynamoDBMapper.generateDeleteTableRequest(LatestPrompt.class);
        TableUtils.deleteTableIfExists(amazonDynamoDB, teamTableRequest);
        TableUtils.deleteTableIfExists(amazonDynamoDB, promptTableRequest);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
//            deleteTable();  //
            char whatisthis = ' ';
            char space = ' ';

            // whatisthis 의 ascii code 를 알고싶어
            // space 랑 같은지 알고싶어
            System.out.println((int)whatisthis);
            System.out.println(whatisthis == space);

            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}