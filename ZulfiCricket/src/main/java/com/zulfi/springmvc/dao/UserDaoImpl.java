package com.zulfi.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zulfi.springmvc.model.Availability;
import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Leagues;
import com.zulfi.springmvc.model.Player;
import com.zulfi.springmvc.model.PlayerCtcl;
import com.zulfi.springmvc.model.Seasons;
import com.zulfi.springmvc.model.Teams;
import com.zulfi.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list
		// page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you
		// want.
		/*
		 * for(User user : users) {
		 * Hibernate.initialize(user.getUserProfiles()); }
		 */
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlayerCtcl> getTeamPlayers() {
		String hql = "from PlayerCtcl where PlayerClub = :pclub and PlayerTeam = :pteam  and isactive = :isactive";
		Query query = session().createQuery(hql);
		query.setParameter("pclub", 10);
		query.setParameter("pteam", 47);
		query.setParameter("isactive", 0);
		List<PlayerCtcl> listPlayer = query.list();
		return listPlayer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlayerCtcl> getTeamPlayersCtcl() {
		String hql = "from PlayerCtcl where PlayerClub = :pclub and PlayerTeam = :pteam  and isactive = :isactive";
		Query query = session().createQuery(hql);
		query.setParameter("pclub", 10);
		query.setParameter("pteam", 47);
		query.setParameter("isactive", 0);
		List<PlayerCtcl> listPlayer = query.list();
		return listPlayer;
	}

	// Submitting player list for team selection
	@Override
	public List<PlayerCtcl> savePlayerForSelection(PlayerCtcl player) {

		String hql = "Update PlayerCtcl set playerAvailability = :availability where playerID = :id";
		Query query = session().createQuery(hql);
		query.setParameter("id", player.getPlayerID());
		query.setParameter("availability", player.getPlayerAvailability());
		int result = query.executeUpdate();
		if (result > 0) {
			System.out.println("Update :  " + result + " rows");

		}
		return null;

	}

	// Opening Player availability
	@Override
	public List<PlayerCtcl> openAvailability(PlayerCtcl player) {

		String hql = "Update PlayerCtcl set playerAvailability = :availability";
		Query query = session().createQuery(hql);
		query.setParameter("availability", player.getPlayerAvailability());
		int result = query.executeUpdate();
		if (result > 0) {
			System.out.println("Update :  " + result + " rows");

		}
		return null;

	}

	@Override
	public void savePlayerInfo(Player player) {

		session().saveOrUpdate(player);

	}

	@Override
	public List<Player> getPlayerInfo(Player player) {
		String hql = "from Player where player_firstName = :fname and player_lastName = :lname ";
		Query query = session().createQuery(hql);
		query.setParameter("fname", player.getPlayer_firstName());
		query.setParameter("lname", player.getPlayer_lastName());
		List<Player> listPlayer = query.list();
		return listPlayer;

	}

	@Override
	public List<PlayerCtcl> saveplayingXI(PlayerCtcl[] player) {
		for (PlayerCtcl aplayer : player) {
			String hql = "Update PlayerCtcl set playerAvailability = :availability where playerID = :id";
			Query query = session().createQuery(hql);
			query.setParameter("id", aplayer.getPlayerID());
			query.setParameter("availability", aplayer.getPlayerAvailability());
			int result = query.executeUpdate();
			if (result > 0) {
				System.out.println("Update :  " + result + " rows");

			}
		}
		return null;
	}

	@Override
	public List<Leagues> getLeaguesList() {
		String hql = "from Leagues";
		Query query = session().createQuery(hql);
		List<Leagues> leagueList = query.list();
		return leagueList;
	}

	@Override
	public List<Seasons> getSeasonsList() {
		String hql = "from Seasons where seasonYear = :sYear ";
		Query query = session().createQuery(hql);
		query.setParameter("sYear", "2017");
		List<Seasons> seasonsList = query.list();
		return seasonsList;
	}

	@Override
	public List<Teams> getTeamsList() {
		String hql = "from Teams where TeamActive = :teamActive ";
		Query query = session().createQuery(hql);
		query.setParameter("teamActive", "1");
		List<Teams> seasonsList = query.list();
		return seasonsList;
	}

	@Override
	public List<Teams> getScheduleList() {
		String hql = "from Teams where TeamActive = :teamActive ";
		Query query = session().createQuery(hql);
		query.setParameter("teamActive", "1");
		List<Teams> seasonsList = query.list();
		return seasonsList;
	}

	@Override
	public List<Ladder> getTeamPoints(String team, String season) {
		int seasonId = 0;
		int teamId = 0;
		// TODO
		team = "";
		Query query;
		String teamName = null;
		List<Seasons> seasonList = getSeasonsList();
		for (Seasons seasonVal : seasonList) {
			if (seasonVal.getSeasonName().equalsIgnoreCase(season)) {
				seasonId = seasonVal.getSeasonID();
			}
		}
		;

		List<Teams> teamsList = getTeamsList();
		for (Teams teamVal : teamsList) {
			if (teamVal.getTeamAbbrev().equalsIgnoreCase(team) && teamVal.getTeamActive().equals("1")) {
				teamName = teamVal.getTeamAbbrev();
				teamId = teamVal.getTeamID();
			}
		}
		;
		String hql = "from Ladder";

		if (team.isEmpty()) {
			hql = "from Ladder where season = :seasonId ";
			teamId = 0;
			query = session().createQuery(hql);
			query.setParameter("seasonId", seasonId);

		} else {
			hql = "from Ladder where team = :teamId and season = :seasonId ";
			query = session().createQuery(hql);
			query.setParameter("teamId", teamId);
			query.setParameter("seasonId", seasonId);
		}

		List<Ladder> pointList = query.list();
		for (Ladder pointVal : pointList) {
			pointVal.setTeamName(teamName);

		}
		return pointList;
	}
}
