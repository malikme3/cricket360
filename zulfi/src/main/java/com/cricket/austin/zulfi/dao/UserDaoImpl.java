package com.cricket.austin.zulfi.dao;

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

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Leagues;
import com.cricket.austin.zulfi.model.Player;
import com.cricket.austin.zulfi.model.PlayerCtcl;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.Teams;
import com.cricket.austin.zulfi.model.User;

@Repository("userDao")
public class UserDaoImpl  implements UserDao {

	static final Logger		logger	= LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private SessionFactory	sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
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
	public List<Seasons> getSeasonsList(String seasonYear) {
		String hql = "from Seasons where seasonYear = :sYear ";
		Query query = session().createQuery(hql);
		query.setParameter("sYear", seasonYear);
		List<Seasons> seasonsList = query.list();
		return seasonsList;
	}

	@Override
	public List<Seasons> getSeason(String seasonYear, String seasonName) {
		String hql = "from Seasons where seasonYear = :sYear ";
		Query query = session().createQuery(hql);
		query.setParameter("sYear", seasonYear);
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
		String Ladder = "from Ladder where team = :teamID ";
		Query query = session().createQuery(Ladder);
		query.setParameter("teamID", 47);
		List<Ladder> list = query.list();
		return list;
	}

	@Override
	public List<Teams> getTeamByTeamAbbrev(String teamAbbrev) {
		String Ladder = "from Teams where TeamAbbrev = :teamAbbrev ";
		Query query = session().createQuery(Ladder);
		query.setParameter("teamAbbrev", teamAbbrev);
		List<Teams> list = query.list();
		return list;
	}
}
