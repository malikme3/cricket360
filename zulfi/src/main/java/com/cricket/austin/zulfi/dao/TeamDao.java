package com.cricket.austin.zulfi.dao;

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

public interface TeamDao {
	List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	List<ScoreCardBasic> getbasicScoreCard(int seasonId);

	List<Seasons> getSeasonGroups(String year);

	List<Schedule> getSchedule(String seasonId);

	List<Map<String, Object>> getDetailedScore(int gameId);

	List<Map<String, Object>> getTeamsName();

	List<Map<String, Object>> getBowlingDetails(int gameId);

	List<Map<String, Object>> getExtraScoreDetails(int gameId);

	void submitResults(SubmitResults scoreDetails);

	void submitScore_gameDetails(ScorecardGameDetails gameDetails);

	void updatFname();

	void updatLname();

	List<Map<String, Object>> findPlayerByTeamId(String teamId);

	List<Map<String, Object>> findPlayer();

	int updateScorecardGameDetails(ScorecardGameDetails details);

	List<Map<String, Object>> findPlayerByIds(List<Integer> ids);

	List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate);

	List<Map<String, Object>> findHowOut();

	int updateInsertScorecardExtrasDetails(SorecardExtrasDetails details);

	int updateInsertScorecardTotalDetails(ScorecardTotalDetails details);

	int updateInsertScorecardFowDetails1(ScorecardFowDetails details);

	int updateScorecardBattingDetails(ScorecardBattingDetails details);

	int updateScorecardBowlingDetails(ScorecardBowlingDetails details);

}
