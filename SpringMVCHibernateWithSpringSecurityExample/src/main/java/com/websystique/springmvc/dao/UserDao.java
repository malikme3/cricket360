package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Player;
import com.websystique.springmvc.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

	List<Player> getAllPlayers();

}

