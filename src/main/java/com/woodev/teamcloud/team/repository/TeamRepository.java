package com.woodev.teamcloud.team.repository;

import com.woodev.teamcloud.team.domain.Team;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface TeamRepository extends CrudRepository<Team, UUID> {
    List<Team> findByNameContaining(String name);

    // GSI entityName-id-index 를 사용하여 조회
    List<Team> findByName(String name);
}
