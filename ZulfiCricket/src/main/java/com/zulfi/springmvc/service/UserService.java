package com.zulfi.springmvc.service;

import java.util.List;

import com.zulfi.springmvc.model.Availability;
import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Leagues;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.Teams;
import com.zulfi.springmvc.model.User;

public interface UserService {

	User findById(int id);

	public List<PlayerCtcl> getTeamPlayers();

	public List<PlayerCtcl> getTeamPlayersCtcl();

	public List<Leagues> getLeaguesList();

	public List<Seasons> getSeasonsList();

	public List<Teams> getTeamsList();

	public List<Teams> getScheduleList();

	public List<Ladder> getTeamPoints(String team, String season);

	User findBySSO(String sso);

	void saveUser(User user);

	void savePlayerInfo(Player player);

	public List<Player> getPlayerInfo(Player player);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String sso);

	List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player);

	List<PlayerCtcl> openAvailability(PlayerCtcl player);

	List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player);

}