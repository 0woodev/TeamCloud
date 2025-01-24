package com.woodev.teamcloud.prompt.repository;

import com.woodev.teamcloud.prompt.domain.Prompt;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
public interface PromptRepository extends CrudRepository<Prompt, UUID> {
    List<Prompt> findByNameContaining(String name);

    // GSI entityName-id-index 를 사용하여 조회
    List<Prompt> findByName(String name);
}
