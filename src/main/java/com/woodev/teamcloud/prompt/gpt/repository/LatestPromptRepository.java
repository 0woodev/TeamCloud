package com.woodev.teamcloud.prompt.gpt.repository;

import com.woodev.teamcloud.prompt.gpt.domain.LatestPrompt;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
public interface LatestPromptRepository extends CrudRepository<LatestPrompt, UUID> {
    // promptNamePromptVersionIndex 사용
    LatestPrompt findFirstByPromptNameAndPromptVersion(String promptName, String version);
}
