package com.zulfi.springmvc.dao;

import java.util.List;

import com.zulfi.springmvc.model.Availability;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	User findBySSO(String sso);

	void save(User user);

	void savePlayerInfo(Player player);

	void deleteBySSO(String sso);

	List<User> findAllUsers();

	List<Availability> getTeamPlayers();

	List<Availability> savePlayerForSelection(Availability availability);

}
