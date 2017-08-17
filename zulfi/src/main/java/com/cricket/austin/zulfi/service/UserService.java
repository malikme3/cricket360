package com.cricket.austin.zulfi.service;

import java.util.List;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Leagues;
import com.cricket.austin.zulfi.model.Player;
import com.cricket.austin.zulfi.model.PlayerCtcl;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.Teams;
import com.cricket.austin.zulfi.model.User;

public interface UserService {

	public List<PlayerCtcl> getTeamPlayers();

	public List<PlayerCtcl> getTeamPlayersCtcl();

	public List<Leagues> getLeaguesList();

	public List<Seasons> getSeasonsList(String seasonYear);

	public List<Teams> getTeamsList();

	public List<Teams> getTeamByTeamAbbrev(String TeamAbbrev);

	public List<Teams> getScheduleList();

	public List<Ladder> getTeamPoints(String team, String season);

	void savePlayerInfo(Player player);

	public List<Player> getPlayerInfo(Player player);

	List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player);

	List<PlayerCtcl> openAvailability(PlayerCtcl player);

	List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player);

}