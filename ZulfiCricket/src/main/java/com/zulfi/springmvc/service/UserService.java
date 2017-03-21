package com.zulfi.springmvc.service;

import java.util.List;

import com.zulfi.springmvc.model.Availability;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.User;

public interface UserService {

	User findById(int id);

	public List<Availability> getTeamPlayers();

	User findBySSO(String sso);

	void saveUser(User user);

	void savePlayerInfo(Player player);

	public List<Player> getPlayerInfo(Player player);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String sso);

	List<Availability> savePlayerForSelection(Availability availability);

}