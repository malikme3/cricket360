package com.zulfi.springmvc.dao;

import java.util.List;

import com.zulfi.springmvc.model.Availability;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	User findBySSO(String sso);

	void save(User user);

	void savePlayerInfo(Player player);

	List<Player> getPlayerInfo(Player player);

	void deleteBySSO(String sso);

	List<User> findAllUsers();

	List<PlayerCtcl> getTeamPlayers();

	List<PlayerCtcl> getTeamPlayersCtcl();

	List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player);

	List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player);

}
