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
import com.zulfi.springmvc.model.Player;
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
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users)
		 * { Hibernate.initialize(user.getUserProfiles());
		 * }
		 */
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Availability> getTeamPlayers() {
		return session().createQuery("from Availability").list();
	}

	// Submitting player list for team selection
	@Override
	public List<Availability> savePlayerForSelection(Availability availability) {

		String hql = "Update Availability set player_availability = :availability where player_id = :id";
		Query query = session().createQuery(hql);
		query.setParameter("id", availability.getPlayer_id());
		query.setParameter("availability", availability.getPlayer_availability());
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

}
