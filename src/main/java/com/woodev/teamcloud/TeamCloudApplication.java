package com.woodev.teamcloud;

import com.woodev.teamcloud.team.repository.TeamRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories(basePackageClasses = TeamRepository.class)
public class TeamCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamCloudApplication.class, args);
    }

}
