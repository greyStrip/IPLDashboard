package com.board.ipldashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import com.board.ipldashboard.data.Team;
import com.board.ipldashboard.model.Match;
import com.board.ipldashboard.repository.MatchRepository;
import com.board.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {
	
	private static final Logger log = LoggerFactory.getLogger(TeamController.class);
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	@Autowired
	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		super();
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	// @GetMapping("/team")
	// public List<Team> getAllTeam(){
	// 	log.info("Getting All Team Data "+TeamController.class);
	// 	Iterable<Team> teams = this.teamRepository.findAll();
	// 	List<Team> teamsList = new LinkedList<>();
	// 	for (Team team : teams) {
	// 		teamsList.add(team);
	// 	}
	// 	return teamsList;
	// }

	@GetMapping("/team")
	public Iterable<Team> getAllTeam(){
		log.info("Getting All Team Data "+TeamController.class);
		return this.teamRepository.findAll();
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		log.info("Getting Team Data "+TeamController.class);
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(this.matchRepository.findLatestMatchByTeam(teamName,4));
		
		return team;
	}

	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
		log.info("Getting Match Data By Year for Team "+teamName+ " - "+year);
		LocalDate startDate = LocalDate.of(year,1,1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);

		// return this.matchRepository.getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(
		// 	teamName,
		// 	teamName,
		// 	startDate,
		// 	endDate
		// 	);

		return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
	}

}
