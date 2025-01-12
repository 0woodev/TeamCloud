package com.woodev.teamcloud.team.service;


import com.woodev.teamcloud.team.domain.Team;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    Team saveTeam(String teamName);
    Team getTeamById(UUID teamId);

    List<Team> getTeams();

    List<Team> getTeamsByName(String teamName);

    String deleteTeamById(String teamId);
    String updateTeam(Team team);
}
