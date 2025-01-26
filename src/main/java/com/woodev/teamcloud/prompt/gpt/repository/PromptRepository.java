package com.woodev.teamcloud.prompt.gpt.repository;

import com.woodev.teamcloud.prompt.gpt.domain.VersioningPrompt;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
public interface PromptRepository extends CrudRepository<VersioningPrompt, UUID> {
    // promptNamePromptVersionIndex 사용
    List<VersioningPrompt> findAllByPromptName(String promptName);
}
