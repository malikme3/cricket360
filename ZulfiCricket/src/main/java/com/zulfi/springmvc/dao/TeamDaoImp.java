package com.zulfi.springmvc.dao;

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

	static final Logger		logger	= LoggerFactory.getLogger(TeamDaoImp.class);
	@Autowired
	private DataSource		dataSource;
	private JdbcTemplate	jdbcTemplate;
	@Autowired
	private UserDao			dao;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Ladder> getTeamPosition(String conferenceAbbrev, String seasonName) {

		String sql = "SELECT t.teamAbbrev , la.*, co.* FROM ladder la "
				+ "INNER JOIN conferencemanagement co ON la.conference = co.ConferenceID "
				+ "INNER JOIN teams t on la.team = t.teamId "
				+ "WHERE la.season in (select SeasonId from seasons s where s.seasonName = ? ) "
				+ "and  co.conferenceAbbrev = ? ORDER BY la.team";

		List<Ladder> teamsPoints = new ArrayList<Ladder>();

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> teamStanding = jdbcTemplate.queryForList(sql,
				new Object[] { seasonName, conferenceAbbrev });

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

		String sql = "SELECT t.teamId, t.teamAbbrev from teams t where t.teamID in (SELECT l.team FROM Ladder l"
				+ " where  season in (Select seasonID from seasons where seasonYear = ? and seasonName = ? )) "
				+ "ORDER BY t.teamId ";

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
		String sql = " SELECT  s.game_id, s.game_date, p.playerFName,p.playerLName,  s.result,a.TeamAbbrev AS AwayAbbrev, h.TeamAbbrev AS HomeAbbrev "
				+ "FROM  scorecard_game_details s "
				+ "INNER JOIN  teams a ON s.awayteam = a.TeamID "
				+ "INNER JOIN  teams h ON s.hometeam = h.TeamID "
				+ "INNER JOIN players p on s.mom = p.playerID	"
				+ "WHERE  s.season= ? AND s.isactive=0	"
				+ "ORDER BY  s.week, s.game_date, s.game_id";

		jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String, Object>> teamNames = jdbcTemplate.queryForList(sql, new Object[] { seasonId });

		for (Map row : teamNames) {

			ScoreCardBasic teamPoints = new ScoreCardBasic();

			teamPoints.setGuest_team((String) row.get("AwayAbbrev"));
			teamPoints.setGame_id((int) row.get("game_id"));
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
	public List<Map<String, Object>> getDetailedScore(int gameId) {

		String sql = "SELECT bf.TeamAbbrev AS BatFirst, bs.TeamAbbrev AS BatSecond, sc.result, tea.teamAbbrev AS WonToss,g.GroundName, "
				+ "CONCAT(pl.PlayerFName ,' ' ,pl.PlayerLName) as PlayerFullName, t.teamAbbrev as batting_team, te.teamAbbrev as bowling_team, "
				+ "s.game_id, s.innings_id, s.batting_position, s.runs, s.balls, s.fours, s.sixes, p.PlayerID AS BatterID, "
				+ "CONCAT(p.PlayerFName,' ',p.PlayerLName) AS BatterFullName, LEFT(p.PlayerFName,1) AS BatterFInitial, h.HowOutID, h.HowOutName,"
				+ " h.HowOutAbbrev, CONCAT(a.PlayerFName,' ',a.PlayerLName) AS AssistFullName, LEFT(a.PlayerFName,1) AS AssistFInitial, "
				+ "CONCAT(b.PlayerLName , ' ',b.PlayerFName) AS BowlerFullName, "
				+ "sco.wickets,sco.total,sco.overs, "
				+ "LEFT(b.PlayerFName,1) AS BowlerFInitial "
				+ "FROM scorecard_batting_details s "
				+ "INNER JOIN teams t on s.team = t.teamid "
				+ "INNER JOIN scorecard_game_details sc on sc.game_id = s.game_id "
				+ "INNER JOIN teams tea on sc.toss_won_id = tea.teamid "
				+ "INNER JOIN teams te on s.opponent = te.teamid "
				+ "INNER JOIN teams bf ON sc.batting_first_id = bf.TeamID "
				+ "INNER JOIN teams bs ON sc.batting_second_id = bs.TeamID "
				+ "INNER JOIN grounds g ON sc.ground_id = g.GroundID "
				+ "INNER JOIN scorecard_total_details sco on sc.game_id = sco.game_id "
				+ "LEFT JOIN players pl ON sc.mom = pl.PlayerID "
				+ "LEFT JOIN players a ON a.PlayerID = s.assist "
				+ "LEFT JOIN players p ON p.PlayerID = s.player_id "
				+ "LEFT JOIN players b ON b.PlayerID = s.bowler "
				+ "INNER JOIN howout h ON h.HowOutID = s.how_out WHERE s.game_id = ? AND s.how_out <> 1 "
				+ "ORDER BY s.batting_position ;";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getBowlingDetails(int gameId) {

		String sql = "SELECT t.teamAbbrev as batting_team, te.teamAbbrev as bowling_team,s.game_id, s.innings_id, s.bowling_position,"
				+ " s.overs, s.maidens, s.runs, s.wickets, s.noballs, s.wides, p.PlayerID AS BowlerID, p.PlayerLName AS BowlerLName, "
				+ "p.PlayerFName AS BowlerFName, LEFT(p.PlayerFName,1) AS BowlerFInitial "
				+ "FROM scorecard_bowling_details s "
				+ "INNER JOIN teams t on s.team = t.teamid "
				+ "INNER JOIN teams te on s.opponent = te.teamid "
				+ "LEFT JOIN players p ON p.PlayerID = s.player_id "
				+ "WHERE s.game_id = ? "
				+ "ORDER BY s.bowling_position and innings_id;";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getExtraScoreDetails(int gameId) {
		String sql = "SELECT s.game_id, s.innings_id, s.batting_position, "
				+ "CONCAT(p.PlayerLName , ' ',p.PlayerFName) AS batsmanFullName, "
				+ "LEFT(p.PlayerFName,1) AS batsmanFInitial, ex.legbyes, ex.byes, ex.wides, ex.noballs, ex.total as extraTotal, tot.wickets as totalWickets, tot.total as totalRuns, tot.overs as totalOvers "
				+ "FROM scorecard_batting_details s "
				+ "LEFT JOIN scorecard_total_details tot ON tot.game_id = s.game_id and tot.innings_id = s.innings_id "
				+ "LEFT JOIN scorecard_extras_details ex ON ex.game_id = s.game_id and ex.innings_id = s.innings_id "
				+ "LEFT JOIN players a ON a.PlayerID = s.assist LEFT JOIN players p ON p.PlayerID = s.player_id "
				+ "LEFT JOIN players b ON b.PlayerID = s.bowler INNER JOIN howout h ON h.HowOutID = s.how_out "
				+ "WHERE s.game_id = ? "
				+ "AND s.how_out = 1 "
				+ "ORDER BY s.innings_id , "
				+ "s.batting_position ;";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> extras = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return extras;

	};

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		List<Seasons> groups = new ArrayList<Seasons>();
		String sql = "SELECT DISTINCT co.conferenceAbbrev, s.seasonName "
				+ "from  conferencemanagement co "
				+ "INNER JOIN ladder la ON la.conference = co.ConferenceID "
				+ "INNER JOIN SEASONS s on s.seasonId = la.season where  s.seasonYear = ? ";
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
		if (seasonId.equalsIgnoreCase("null")) {
			seasonId = null;
		}
		List<Schedule> schedule = new ArrayList<Schedule>();
		String sql = " SELECT s.seasonName,t.teamabbrev as awayteam, th.teamAbbrev as hometeam, p.playerFname as umpireFName, p.playerLName as umpireLName,sch.date,DATE_FORMAT(sch.date, '%b %e') as "
				+ "formatted_date,s.seasonId, sch.week, grn.GroundName as ground "
				+ "FROM schedule sch "
				+ "INNER JOIN players p on sch.umpire1 =  p.playerID "
				+ "INNER JOIN teams t on sch.awayteam = t.teamId "
				+ "INNER JOIN teams th on sch.hometeam = th.teamId "
				+ "INNER JOIN seasons s on sch.season = s.seasonId , grounds grn "
				+ "WHERE  sch.venue = grn.GroundID AND sch.date >= NOW() and s.seasonId = IFNULL(?, s.seasonId ) ORDER BY sch.date, sch.id ";

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
