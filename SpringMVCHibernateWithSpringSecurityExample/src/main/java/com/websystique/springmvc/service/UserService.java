package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Player;
import com.websystique.springmvc.model.User;

public interface UserService {

	User findById(int id);

	public List<Player> getAllPlayers();

	User findBySSO(String sso);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String sso);

}