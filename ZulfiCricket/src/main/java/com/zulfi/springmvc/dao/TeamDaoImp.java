package com.zulfi.springmvc.dao;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.Schedule;
import com.zulfi.springmvc.model.ScoreCardBasic;
import com.zulfi.springmvc.model.Seasons;

@Repository("teamDao")
public class TeamDaoImp implements TeamDao {

	static final Logger logger = LoggerFactory.getLogger(TeamDaoImp.class);
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserDao dao;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Ladder> getTeamPosition(String conferenceAbbrev, String seasonName) {

		String sql = "SELECT t.teamAbbrev , la.*, co.* FROM world.ladder la " + "INNER JOIN world.conferencemanagement co ON la.conference = co.ConferenceID "
				+ "INNER JOIN world.teams t on la.team = t.teamId " + "WHERE la.season in (select SeasonId from world.seasons s where s.seasonName = ? ) "
				+ "and  co.conferenceAbbrev = ? ORDER BY la.team";

		List<Ladder> teamsPoints = new ArrayList<Ladder>();

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> teamStanding = jdbcTemplate.queryForList(sql, new Object[] { seasonName, conferenceAbbrev });

		for (Map row : teamStanding) {
			Ladder teamPoints = new Ladder();

			teamPoints.setTeam((int) row.get("team"));
			teamPoints.setTeamAbbrev((String) row.get("teamAbbrev"));
			teamPoints.setPlayed((int) row.get("played"));
			teamPoints.setWon((int) row.get("won"));
			teamPoints.setLost((int) row.get("lost"));
			teamPoints.setTied((int) row.get("tied"));
			teamPoints.setPenalty((int) row.get("penalty"));
			teamPoints.setTotalpoints((int) row.get("totalpoints"));
			teamPoints.setConferenceAbbrev((String) row.get("conferenceAbbrev"));
			// TODO nrr for zero value check
			/*
			 * if (row.get("nrr"). { teamPoints.setNrr((double) row.get("nrr"));
			 * } else { teamPoints.setNrr(0); };
			 */

			teamsPoints.add(teamPoints);

		}
		;

		return teamsPoints;
	}

	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName) {
		List<Ladder> teamsNames = new ArrayList<Ladder>();

		String sql = "SELECT t.teamId, t.teamAbbrev from world.teams t where t.teamID in (SELECT l.team FROM world.Ladder l"
				+ " where  season in (Select seasonID from world.seasons where seasonYear = ? and seasonName = ? )) ORDER BY t.teamId ";

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> teamNames = jdbcTemplate.queryForList(sql, new Object[] { seasonYear, seasonName });

		for (Map row : teamNames) {

			Ladder teamPoints = new Ladder();
			teamPoints.setTeam((int) row.get("teamId"));
			teamPoints.setTeamName((String) row.get("TeamAbbrev"));
			teamsNames.add(teamPoints);
		}

		return teamsNames;

	};

	public List<ScoreCardBasic> getbasicScoreCard(int seasonId) {

		List<ScoreCardBasic> teamsNames = new ArrayList<ScoreCardBasic>();
		String sql = " SELECT  s.game_date, p.playerFName,p.playerLName,  s.result,a.TeamAbbrev AS AwayAbbrev, h.TeamAbbrev AS HomeAbbrev FROM  world.scorecard_game_details s INNER JOIN  world.teams a ON s.awayteam = a.TeamID"
				+ "	INNER JOIN  world.teams h ON s.hometeam = h.TeamID INNER JOIN world.players p on s.mom = p.playerID	WHERE  s.season= ? AND s.isactive=0	ORDER BY  s.week, s.game_date, s.game_id";

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> teamNames = jdbcTemplate.queryForList(sql, new Object[] { seasonId });

		for (Map row : teamNames) {

			ScoreCardBasic teamPoints = new ScoreCardBasic();

			teamPoints.setGuest_team((String) row.get("AwayAbbrev"));
			teamPoints.setHost_team((String) row.get("HomeAbbrev"));
			teamPoints.setPlayer_first_name((String) row.get("playerFName"));
			teamPoints.setPlayer_last_name((String) row.get("playerLName"));
			teamPoints.setMatch_date((Date) row.get("game_date"));
			teamPoints.setMatch_status((String) row.get("result"));

			teamsNames.add(teamPoints);
		}

		return teamsNames;
	}

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		List<Seasons> groups = new ArrayList<Seasons>();
		String sql = "SELECT DISTINCT co.conferenceAbbrev, s.seasonName from  WORLD.conferencemanagement co INNER JOIN WORLD.ladder la ON la.conference = co.ConferenceID INNER JOIN WORLD.SEASONS s on s.seasonId = la.season where  s.seasonYear = ? ";
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> sGroups = jdbcTemplate.queryForList(sql, new Object[] { year });

		for (Map row : sGroups) {
			Seasons seasonGroups = new Seasons();
			seasonGroups.setSeasonName((String) row.get("seasonName"));
			seasonGroups.setGroupCategory((String) row.get("conferenceAbbrev"));

			groups.add(seasonGroups);

		}

		return groups;
	}

	@Override
	public List<Schedule> getSchedule(String seasonId) {
		if(seasonId.equalsIgnoreCase("null")){
		seasonId = null;
		}
		List<Schedule> schedule = new ArrayList<Schedule>();
		String sql = " SELECT s.seasonName,t.teamabbrev as awayteam, th.teamAbbrev as hometeam, p.playerFname as umpireFName, p.playerLName as umpireLName,sch.date,DATE_FORMAT(sch.date, '%b %e') as "
				+ "formatted_date,s.seasonId, sch.week, grn.GroundName as ground FROM WORLD.schedule sch " + "INNER JOIN WORLD.players p on sch.umpire1 =  p.playerID "
				+ "INNER JOIN WORLD.teams t on sch.awayteam = t.teamId " + "INNER JOIN WORLD.teams th on sch.hometeam = th.teamId "
				+ "INNER JOIN WORLD.seasons s on sch.season = s.seasonId , WORLD.grounds grn WHERE  sch.venue = grn.GroundID AND sch.date >= NOW() and s.seasonId = IFNULL(?, s.seasonId ) ORDER BY sch.date, sch.id ";

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> listSchedule = jdbcTemplate.queryForList(sql, new Object[] { seasonId });
		for (Map row : listSchedule) {
			Schedule schd = new Schedule();
			schd.setSeasonName((String) row.get("seasonName"));
			schd.setAwayTeam((String) row.get("awayteam"));
			schd.setHomeTeam((String) row.get("hometeam"));
			schd.setUmpireFName((String) row.get("umpireFName"));
			schd.setUmpireLName((String) row.get("umpireLName"));
			schd.setDate((Date) row.get("date"));
			schd.setFormattedDate((String) row.get("formatted_date"));
			schd.setSeasonId((int) row.get("seasonId"));
			schd.setWeek((int) row.get("week"));
			schd.setGround((String) row.get("ground"));
			schedule.add(schd);
		}
		return schedule;

	}

}
