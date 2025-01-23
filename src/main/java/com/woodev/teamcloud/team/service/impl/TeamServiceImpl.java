package com.woodev.teamcloud.team.service.impl;

import com.woodev.teamcloud.team.domain.Team;
import com.woodev.teamcloud.team.repository.TeamRepository;
import com.woodev.teamcloud.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    @Override
    public Team saveTeam(String teamName) {
        Team team = Team.builder().name(teamName).build();
        return teamRepository.save(team);
    }

    @Override
    public Team getTeamById(UUID teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }


    @Override
    public List<Team> getTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    @Override
    public List<Team> getTeamsByName(String teamName) {
        return teamRepository.findByName(teamName);
    }

    @Override
    public String deleteTeamById(String teamId) {
        return null;
    }

    @Override
    public String updateTeam(Team team) {
        return null;
    }
}
