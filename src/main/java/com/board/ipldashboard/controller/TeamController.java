package com.board.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.board.ipldashboard.data.Team;
import com.board.ipldashboard.repository.MatchRepository;
import com.board.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	@Autowired
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		super();
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchByTeam(teamName,4));
		
		return team;
	}

}
