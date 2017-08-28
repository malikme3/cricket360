package com.cricket.austin.zulfi.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Schedule;
import com.cricket.austin.zulfi.model.ScoreCardBasic;
import com.cricket.austin.zulfi.model.ScorecardBattingDetails;
import com.cricket.austin.zulfi.model.ScorecardBowlingDetails;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

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

	List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate);

	List<Map<String, Object>> findPlayerByTeamId(String teamId);

	List<Map<String, Object>> findPlayer() throws Exception;

	int updateScorecardGameDetails(ScorecardGameDetails details);

	List<Map<String, Object>> findPlayerByIds(List<Integer> ids);

	List<Map<String, Object>> findHowOut();

	int updateInsertScorecardExtrasDetails(SorecardExtrasDetails details);

	int updateInsertScorecardTotalDetails(ScorecardTotalDetails details);

	int updateInsertScorecardFowDetails1(ScorecardFowDetails details);

	int updateScorecardBattingDetails(ScorecardBattingDetails details);

	int updateScorecardBowlingDetails(ScorecardBowlingDetails details);

}
