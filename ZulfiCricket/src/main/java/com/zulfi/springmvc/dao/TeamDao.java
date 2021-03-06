package com.zulfi.springmvc.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.ScorecardBattingDetails;
import com.zulfi.springmvc.model.ScorecardBowlingDetails;
import com.zulfi.springmvc.model.ScorecardFowDetails;
import com.zulfi.springmvc.model.ScorecardGameDetails;
import com.zulfi.springmvc.model.ScorecardTotalDetails;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.SorecardExtrasDetails;
import com.zulfi.springmvc.model.SubmitResults;

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

	int updateScorecardExtrasDetails(SorecardExtrasDetails details);

	int updateScorecardTotalDetails(ScorecardTotalDetails details);

	int updateScorecardFowDetails(ScorecardFowDetails details);

	int updateScorecardBattingDetails(ScorecardBattingDetails details);

	int updateScorecardBowlingDetails(ScorecardBowlingDetails details);

}
