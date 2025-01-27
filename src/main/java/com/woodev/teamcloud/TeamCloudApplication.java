package com.woodev.teamcloud;

import com.woodev.teamcloud.prompt.gpt.repository.VersioningPromptRepository;
import com.woodev.teamcloud.prompt.gpt.repository.GPTAPIKeyRepository;
import com.woodev.teamcloud.team.repository.TeamRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories(basePackageClasses = {
        TeamRepository.class,
        VersioningPromptRepository.class,
        GPTAPIKeyRepository.class
})
public class TeamCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamCloudApplication.class, args);
    }

}
