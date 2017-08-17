package com.cricket.austin.zulfi.dao;

import java.util.List;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Leagues;
import com.cricket.austin.zulfi.model.Player;
import com.cricket.austin.zulfi.model.PlayerCtcl;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.Teams;
import com.cricket.austin.zulfi.model.User;

public interface UserDao {

	
	List<PlayerCtcl> getTeamPlayers();

	List<PlayerCtcl> getTeamPlayersCtcl();

	List<Leagues> getLeaguesList();

	List<Seasons> getSeasonsList(String seasonYear);

	List<Seasons> getSeason(String seasonYear, String seasonName);

	List<Teams> getTeamsList();

	List<Teams> getTeamByTeamAbbrev(String teamAbbrev);

	List<Teams> getScheduleList();

	List<Ladder> getTeamPoints(String team, String season);

	List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player);

	List<PlayerCtcl> openAvailability(PlayerCtcl player);

	List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player);

	void savePlayerInfo(Player player);

	List<Player> getPlayerInfo(Player player);

}
