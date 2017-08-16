package com.cricket.austin.zulfi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricket.austin.zulfi.dao.UserDao;
import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Leagues;
import com.cricket.austin.zulfi.model.Player;
import com.cricket.austin.zulfi.model.PlayerCtcl;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.Teams;
import com.cricket.austin.zulfi.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	

	@Override
	public List<PlayerCtcl> getTeamPlayers() {
		return dao.getTeamPlayers();
	}

	@Override
	public List<PlayerCtcl> getTeamPlayersCtcl() {
		return dao.getTeamPlayersCtcl();
	}

	@Override
	public List<Leagues> getLeaguesList() {
		return dao.getLeaguesList();
	}

	@Override
	public List<Seasons> getSeasonsList(String seasonYear) {
		return dao.getSeasonsList(seasonYear);
	}

	@Override
	public List<Teams> getTeamsList() {
		return dao.getTeamsList();
	}

	@Override
	public List<Teams> getScheduleList() {
		return dao.getScheduleList();
	}

	@Override
	public List<Ladder> getTeamPoints(String team, String season) {
		return dao.getTeamPoints(team, season);
	}

	@Override
	public List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player) {
		return dao.savePlayerForSelection(player);

	}

	@Override
	public List<PlayerCtcl> openAvailability(PlayerCtcl player) {
		return dao.openAvailability(player);

	}

	@Override
	public void savePlayerInfo(Player player) {
		dao.savePlayerInfo(player);

	}

	@Override
	public List<Player> getPlayerInfo(Player player) {
		return dao.getPlayerInfo(player);

	}

	@Override
	public List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player) {
		return dao.saveplayingXI(player);
	}

	@Override
	public List<Teams> getTeamByTeamAbbrev(String TeamAbbrev) {
		return dao.getTeamByTeamAbbrev(TeamAbbrev);
	}


}
