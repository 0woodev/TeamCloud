package com.woodev.teamcloud.prompt.gpt.repository;

import com.woodev.teamcloud.prompt.gpt.domain.GPTAPIKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@EnableScan
public interface GPTAPIKeyRepository extends CrudRepository<GPTAPIKey, UUID> {
    List<GPTAPIKey> findAllByEntityName(String entityName);
}
