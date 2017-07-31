package com.zulfi.springmvc.dao;

import java.util.List;
import java.util.Map;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.Seasons;
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
	
	void updatFname();
	void updatLname();

}
