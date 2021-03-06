package com.zulfi.springmvc.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zulfi.springmvc.dao.TeamDao;
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

@Service("teamServiceMatch")
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
	public List<Schedule> getSchedule(String seasonId) {
		return teamDao.getSchedule(seasonId);
	}

	@Override
	public List<Map<String, Object>> getDetailedScore(int gameId) {
		return teamDao.getDetailedScore(gameId);
	}

	@Override
	public List<Map<String, Object>> getTeamsName() {
		return teamDao.getTeamsName();
	}

	@Override
	public List<Map<String, Object>> getBowlingDetails(int gameId) {
		return teamDao.getBowlingDetails(gameId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public List<Map<String, Object>> getExtraScoreDetails(int gameId) throws Exception {
		/*
		 * try {
		 * update();
		 * insert();
		 * } catch (Exception ex) {
		 * System.out.println(ex);
		 * throw ex;
		 * }
		 */
		return teamDao.getExtraScoreDetails(gameId);
	}

	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor =
	// {Exception.class})
	public void update() throws Exception {
		teamDao.updatFname();
	}

	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor =
	// {Exception.class})
	public void insert() throws Exception {
		try {
			teamDao.updatLname();
		} catch (Exception ex) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw ex;
		}
	}

	@Override
	public void submitResults(SubmitResults scoreDetails) {
		teamDao.submitResults(scoreDetails);
	}

	@Override
	public void submitScore_gameDetails(ScorecardGameDetails gameDetails) {
		teamDao.submitScore_gameDetails(gameDetails);

	}

	@Override
	public List<Map<String, Object>> findPlayerByTeamId(String teamId) {
		return teamDao.findPlayerByTeamId(teamId);

	}

	@Override
	public List<Map<String, Object>> findPlayer() {
		return teamDao.findPlayer();

	}

	@Override
	public List<Map<String, Object>> findPlayerByIds(List<Integer> ids) {
		return teamDao.findPlayerByIds(ids);

	}

	@Override
	public int updateScorecardGameDetails(ScorecardGameDetails details) {
		return teamDao.updateScorecardGameDetails(details);

	}

	@Override
	public List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate) {
		return teamDao.findMatchByDate(homeTeam, awayTeam, matchDate);

	}

	@Override
	public List<Map<String, Object>> findHowOut() {
		return teamDao.findHowOut();

	}

	@Override
	public int updateScorecardExtrasDetails(SorecardExtrasDetails details) {
		return teamDao.updateScorecardExtrasDetails(details);
	}

	@Override
	public int updateScorecardTotalDetails(ScorecardTotalDetails details) {
		return teamDao.updateScorecardTotalDetails(details);
	}

	@Override
	public int updateScorecardFowDetails(ScorecardFowDetails details) {
		return teamDao.updateScorecardFowDetails(details);
	}

	@Override
	public int updateScorecardBattingDetails(ScorecardBattingDetails details) {
		return teamDao.updateScorecardBattingDetails(details);
	}

	@Override
	public int updateScorecardBowlingDetails(ScorecardBowlingDetails details) {
		return teamDao.updateScorecardBowlingDetails(details);
	}

}
