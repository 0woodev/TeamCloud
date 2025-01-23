package com.woodev.teamcloud.util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
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
        teamTableRequest.setProvisionedThroughput(
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

        teamTableRequest.setGlobalSecondaryIndexes(List.of(entityNameIdIndex));
//        CreateTableRequest musicCollectionTableRequest = dynamoDBMapper
//                .generateCreateTableRequest(MusicCollection.class);
//        musicCollectionTableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, teamTableRequest);
    }

    void deleteTable() {
        DeleteTableRequest customerTableRequest = dynamoDBMapper.generateDeleteTableRequest(Team.class);
        TableUtils.deleteTableIfExists(amazonDynamoDB, customerTableRequest);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
//            deleteTable();
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}