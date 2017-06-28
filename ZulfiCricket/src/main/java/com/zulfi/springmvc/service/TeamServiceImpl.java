package com.zulfi.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zulfi.springmvc.dao.TeamDao;
import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.Seasons;

@Service("teamServiceMatch")
@Transactional
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;

	@Override
	public List<Ladder> getTeamPosition(String seasonYear, String seasonName) {
		return teamDao.getTeamPosition(seasonYear, seasonName);
	}

	@Override
	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName) {
		return teamDao.getTeamsIdTeamsAbbrv(seasonYear, seasonName);
	}

	@Override
	public List<ScoreCardBasic> getbasicScoreCard(int seasonId) {
		return teamDao.getbasicScoreCard(seasonId);
	}

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		return teamDao.getSeasonGroups(year);
	}

	@Override
	public List<Schedule> getSchedule() {
		return teamDao.getSchedule();
	}

}
