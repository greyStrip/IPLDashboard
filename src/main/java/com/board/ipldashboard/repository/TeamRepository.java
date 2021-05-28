package com.board.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.board.ipldashboard.data.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByTeamName(String teamName);
}
