package com.zulfi.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zulfi.springmvc.dao.UserDao;
import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Leagues;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.Teams;
import com.zulfi.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

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
