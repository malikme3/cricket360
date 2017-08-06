package com.zulfi.springmvc.service;

import java.util.List;
import java.util.Map;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.ScorecardGameDetails;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.SubmitResults;

public interface TeamService {

	public List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	public List<ScoreCardBasic> getbasicScoreCard(int seasonId);

	public List<Seasons> getSeasonGroups(String year);

	public List<Schedule> getSchedule(String seasonId);

	public List<Map<String, Object>> getDetailedScore(int gameId);

	public List<Map<String, Object>> getBowlingDetails(int gameId);

	public List<Map<String, Object>> getExtraScoreDetails(int gameId) throws Exception;

	public List<Map<String, Object>> getTeamsName() throws Exception;

	public void submitResults(SubmitResults scoreDetails);

	public void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	List<Map<String, Object>> findPlayerByTeamId(String teamId);

	List<Map<String, Object>> findPlayer();

	int updateScorecardGameDetails(ScorecardGameDetails details);

}