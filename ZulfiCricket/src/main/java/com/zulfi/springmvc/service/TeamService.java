package com.zulfi.springmvc.service;

import java.util.List;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.Seasons;

public interface TeamService {

	public List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	public List<ScoreCardBasic> getbasicScoreCard(int seasonId);

	public List<Seasons> getSeasonGroups(String year);

	List<Schedule> getSchedule();

}