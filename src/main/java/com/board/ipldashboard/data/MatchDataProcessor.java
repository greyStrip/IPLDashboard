package com.board.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.board.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public Match process(MatchInput matchInput) throws Exception {
		Match match = new Match();
		match.setId(Long.parseLong(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
		match.setVenue(matchInput.getVenue());

		String firstInningsTeam, secondInningsTeam;
		String tossWinner = matchInput.getTossWinner();

		String otherTeam = matchInput.getTeam1().equals(tossWinner) 
				? matchInput.getTeam2() : matchInput.getTeam1();

		if ("bat".equals(matchInput.getTossDecision())) {
			firstInningsTeam = tossWinner;
			secondInningsTeam = otherTeam;
		} else {
			firstInningsTeam = otherTeam;
			secondInningsTeam = tossWinner;
		}
		
		match.setTeam1(firstInningsTeam);
		match.setTeam2(secondInningsTeam);
		
		match.setTossWinner(matchInput.getTossWinner());
		match.setTossDecision(matchInput.getTossDecision());
		match.setMatchWinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResultMargin());
		
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		
		return match;
	}

}
