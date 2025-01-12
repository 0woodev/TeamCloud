package com.woodev.teamcloud.team.controller;

import com.woodev.teamcloud.team.domain.Team;
import com.woodev.teamcloud.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") UUID teamId) {
        return teamService.getTeamById(teamId);
    }

    @PostMapping("")
    public Team saveTeam(@RequestBody String teamName) {
        return teamService.saveTeam(teamName);
    }

    @GetMapping("")
    public List<Team> search(
            @RequestParam(required = false) String teamName
    ) {
        if (teamName != null) {
            return teamService.getTeamsByName(teamName);
        }
        return teamService.getTeams();
    }
}
