package com.cricket.austin.zulfi.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cricket.austin.zulfi.model.Ladder;
import com.cricket.austin.zulfi.model.Schedule;
import com.cricket.austin.zulfi.model.ScoreCardBasic;
import com.cricket.austin.zulfi.model.ScorecardBattingDetails;
import com.cricket.austin.zulfi.model.ScorecardBowlingDetails;
import com.cricket.austin.zulfi.model.ScorecardFowDetails;
import com.cricket.austin.zulfi.model.ScorecardGameDetails;
import com.cricket.austin.zulfi.model.ScorecardTotalDetails;
import com.cricket.austin.zulfi.model.Seasons;
import com.cricket.austin.zulfi.model.SorecardExtrasDetails;
import com.cricket.austin.zulfi.model.SubmitResults;

@Repository
public class TeamDaoImpl implements TeamDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	static final Logger logger = LoggerFactory.getLogger(TeamDaoImpl.class);

	@Override
	public List<Ladder> getTeamPosition(String conferenceAbbrev, String seasonName) {

		String sql = "SELECT t.teamAbbrev , la.*, co.* FROM ladder la "
				+ "INNER JOIN conferencemanagement co ON la.conference = co.ConferenceID "
				+ "INNER JOIN teams t on la.team = t.teamId "
				+ "WHERE la.season in (select SeasonId from seasons s where s.seasonName = ? ) "
				+ "and  co.conferenceAbbrev = ? ORDER BY la.team";

		List<Ladder> teamsPoints = new ArrayList<Ladder>();

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

		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql, new Object[] { gameId });
		return detailed_score;
	}

	@Override
	public List<Map<String, Object>> getTeamsName() {

		String sql = "select teamabbrev as label, teamid as value from Teams where TeamActive = '1'";

		List<Map<String, Object>> detailed_score = jdbcTemplate.queryForList(sql);
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

		List<Map<String, Object>> extras = jdbcTemplate.queryForList(sql, new Object[] { gameId });

		/*
		 * updatFname();
		 * try{
		 * updatLname();
		 * } catch (Exception e){
		 * throw e;
		 * }
		 */
		return extras;

	};

	@Override
	public List<Seasons> getSeasonGroups(String year) {
		List<Seasons> groups = new ArrayList<Seasons>();
		String sql = "SELECT DISTINCT co.conferenceAbbrev, s.seasonName "
				+ "from  conferencemanagement co "
				+ "INNER JOIN ladder la ON la.conference = co.ConferenceID "
				+ "INNER JOIN SEASONS s on s.seasonId = la.season where  s.seasonYear = ? ";

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

	@SuppressWarnings("unused")
	@Override
	public void updatFname() {
		String sql = "UPDATE players SET PlayerFName = '1', PlayerLName = '34' WHERE PlayerID = 1";

		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);

	}

	@SuppressWarnings("unused")
	@Override
	public void updatLname() {

		String sql = "UPDATE players SET PlayerLNfame = 'f000-786', PlayerLName = 'l000-786' WHERE PlayerID = 1";
		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);
	}

	@Override
	public void submitResults(SubmitResults scoreDetails) {
		String sql = "UPDATE RESULTS set played=played+?, won=won+?, lost=lost+?, tied=tied+?, nr=nr+? where team_id = ?";
		int rows = jdbcTemplate.update(sql, scoreDetails.getPlayed(), scoreDetails.getWon(), scoreDetails.getLost(),
				scoreDetails.getTied(), scoreDetails.getNr(), scoreDetails.getTeamID());
		logger.info("rows are ::" + rows);

	}

	@Override
	public void submitScore_gameDetails(ScorecardGameDetails gameDetails) {
		String sql = "INSERT INTO scorecard_game_details "
				+ "(league_id,season,week,awayteam,hometeam,game_date,result_won_id,forfeit,mom,umpire1,umpire2,maxovers,isactive) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";

		Object param = new Object[] { gameDetails.getLeagueId(), gameDetails.getSeason(), gameDetails.getWeek(),
				gameDetails.getAwayteam(), gameDetails.getHometeam(),
				gameDetails.getGameDate(), gameDetails.getResultWonId(), gameDetails.getForfeit(),
				gameDetails.getMom(), gameDetails.getUmpire1(), gameDetails.getUmpire2(),
				gameDetails.getMaxovers() };

		int rows = jdbcTemplate.update(sql, param);
		logger.info("rows are ::" + rows);
	}

	@Override
	public List<Map<String, Object>> findMatchByDate(int homeTeam, int awayTeam, Date matchDate) {
		String sql = "SELECT s.* "
				+ "FROM scorecard_game_details s "
				+ "INNER JOIN grounds g ON s.ground_id = g.GroundID "
				+ "INNER JOIN teams a ON s.awayteam = a.TeamID "
				+ "INNER JOIN teams h ON s.hometeam = h.TeamID "
				+ "LEFT JOIN teams u ON s.umpires = u.TeamID "
				+ "LEFT JOIN teams t ON s.toss_won_id = t.TeamID "
				+ "INNER JOIN teams b ON s.batting_first_id = b.TeamID "
				+ "INNER JOIN teams n ON s.batting_second_id = n.TeamID "
				+ "WHERE  s.hometeam = ? AND s.awayteam = ? AND s.game_date = ? ";
		List<Map<String, Object>> match = jdbcTemplate.queryForList(sql, homeTeam, awayTeam, matchDate);
		return match;
	}

	@Override
	public List<Map<String, Object>> findPlayerByTeamId(String teamId) {
		String sql = "SELECT  concat(PlayerFName, ' ', PlayerLName) as label, playerId as value "
				+ "FROM players where playerTeam = IFNULL(?, playerTeam ) and isactive = 0 ORDER BY PlayerFName,PlayerLName";

		List<Map<String, Object>> playersList = jdbcTemplate.queryForList(sql, teamId);
		return playersList;
	}

	@Override
	public List<Map<String, Object>> findPlayer() {

		String sql = "SELECT CONCAT(p.PlayerFName, ' ', p.PlayerLName) as label, p.playerId as value  "
				+ "FROM PLAYERS p INNER JOIN TEAMS t ON t.TeamID = p.PlayerTeam "
				+ "WHERE p.isactive = 0  ORDER BY p.PlayerFName,p.PlayerLName;";

		List<Map<String, Object>> playersList = jdbcTemplate.queryForList(sql);
		return playersList;
	}

	@Override
	public List<Map<String, Object>> findPlayerByIds(List<Integer> ids) {
		String sql = "SELECT CONCAT(p.PlayerFName, ' ', p.PlayerLName) as label, p.PlayerId as value  "
				+ "FROM PLAYERS p INNER JOIN TEAMS t ON t.TeamID = p.PlayerTeam "
				+ "WHERE p.playerTeam IN (:teamsIds)  and p.isactive = 0 ORDER BY PlayerFName,PlayerLName";

		Map<String, List<Integer>> paramMap = Collections.singletonMap("teamsIds", ids);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<Map<String, Object>> playersList = template.queryForList(sql, paramMap);
		logger.info("PlayersList is :: " + playersList);
		return playersList;
	}

	@Override
	public int updateScorecardGameDetails(ScorecardGameDetails details) {
		String sql = "INSERT INTO scorecard_game_details "
				+ "(league_id,season,week,awayteam,hometeam,game_date,result_won_id,forfeit,mom,umpire1,umpire2,maxovers,isactive) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";

		int rows = jdbcTemplate.update(sql, details.getLeagueId(), details.getSeason(), details.getWeek(),
				details.getAwayteam(), details.getHometeam(),
				details.getGameDate(), details.getResultWonId(), details.getForfeit(), details.getMom(),
				details.getUmpire1(),
				details.getUmpire2(), details.getMaxovers());
		return rows;
	}

	@Override
	public List<Map<String, Object>> findHowOut() {
		String sql = "SELECT howOutName as label , howOutId as value from howout order by howOutId";
		List<Map<String, Object>> howOutList = jdbcTemplate.queryForList(sql);
		return howOutList;

	}

	@Override
	public int updateScorecardBattingDetails(ScorecardBattingDetails details) {
		String sql = "scorecard_batting_details "
				+ "(game_id,season,innings_id,player_id,batting_position,how_out,runs,assist,bowler,balls,fours,sixes,notout,team,opponent) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateScorecardBowlingDetails(ScorecardBowlingDetails details) {
		String sql = "INSERT INTO scorecard_bowling_details "
				+ "(game_id,season,innings_id,player_id,bowling_position,overs,maidens,runs,wickets,noballs,wides,team,opponent) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql);
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardTotalDetails(ScorecardTotalDetails totalDetails) {

		int rows = 0;
		if (totalDetails.getGame_id() > 0) {
			try {
				rows = updateTotalsDetails(totalDetails);
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertTotalsDetails(totalDetails);
			}

		} else {
			logger.warn("Opps GameId is not provided");
			return rows;
		}
		return rows;

	}

	private int updateTotalsDetails(ScorecardTotalDetails totalDetails) {

		String sql = "UPDATE scorecard_total_details SET team=? ,wickets=? ,total=? ,overs=?  WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, totalDetails.getTeam(), totalDetails.getWickets(), totalDetails.getTotal(),
				totalDetails.getOvers(), totalDetails.getGame_id(), totalDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;
	}

	private int insertTotalsDetails(ScorecardTotalDetails totalDetails) {

		String sql = "INSERT INTO scorecard_total_details "
				+ "(game_id,innings_id,team,wickets,total,overs) "
				+ "VALUES (?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, totalDetails.getGame_id(), totalDetails.getInnings_id(),
				totalDetails.getTeam(), totalDetails.getWickets(), totalDetails.getTotal(), totalDetails.getOvers());
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardFowDetails1(ScorecardFowDetails fowDetails) {
		int rows = 0;
		if (fowDetails.getGame_id() > 0) {
			try {
				rows = updateFowDetails(fowDetails);
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertFowDetails(fowDetails);
			}

		} else {
			logger.warn("Opps GameId is not provided");
			return rows;
		}
		return rows;

	}

	public int updateFowDetails(ScorecardFowDetails fowDetails) {
		String sql = "UPDATE SCORECARD_FOW_DETAILS SET fow1 = ?,fow2= ?,fow3= ?,fow4= ?,fow5= ?,fow6= ?,fow7= ?,fow8= ?,fow9= ?,fow10= ? WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, fowDetails.getFow1(),
				fowDetails.getFow2(), fowDetails.getFow3(), fowDetails.getFow4(), fowDetails.getFow5(),
				fowDetails.getFow6(), fowDetails.getFow7(), fowDetails.getFow8(), fowDetails.getFow9(),
				fowDetails.getFow10(), fowDetails.getGame_id(), fowDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;

	};

	public int insertFowDetails(ScorecardFowDetails fowDetails) {
		String sql = "INSERT INTO SCORECARD_FOW_DETAILS "
				+ "(game_id,innings_id,fow1,fow2,fow3,fow4,fow5,fow6,fow7,fow8,fow9,fow10) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, fowDetails.getGame_id(), fowDetails.getInnings_id(), fowDetails.getFow1(),
				fowDetails.getFow2(), fowDetails.getFow3(), fowDetails.getFow4(), fowDetails.getFow5(),
				fowDetails.getFow6(), fowDetails.getFow7(), fowDetails.getFow8(), fowDetails.getFow9(),
				fowDetails.getFow10());
		logger.info("rows are ::" + rows);
		return rows;
	}

	@Override
	public int updateInsertScorecardExtrasDetails(SorecardExtrasDetails extrasDetails) {
		int rows = 0;

		if (extrasDetails.getGame_id() > 0
				&& (extrasDetails.getInnings_id() == 1 || extrasDetails.getInnings_id() == 2)) {
			try {
				rows = updateExtrasDetails(extrasDetails);

				// Making sure to Insert record if no row is updated
				if (rows == 0) {
					logger.warn("NO row is updated, Try to insert data");
					rows = insertExtrasDetails(extrasDetails);
				}
			} catch (Exception ex) {
				logger.info("Update failed, Try to insert data");
				rows = insertExtrasDetails(extrasDetails);
			}

		} else {
			logger.error("Opps Game Id or Innings Id is not Correct");
			return rows;
		}
		return rows;

	}

	private int insertExtrasDetails(SorecardExtrasDetails extrasDetails) {
		String sql = "INSERT INTO scorecard_extras_details "
				+ "(game_id,innings_id,legbyes,byes,wides,noballs,total) "
				+ "VALUES (?,?,?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, extrasDetails.getGame_id(), extrasDetails.getInnings_id(),
				extrasDetails.getLegbyes(), extrasDetails.getByes(), extrasDetails.getWides(),
				extrasDetails.getNoballs(), extrasDetails.getTotal());
		logger.info("R # of rows inserted are ::" + rows);
		if (rows < 1) {
			logger.error("Failed to Insert data");
		}
		return rows;
	}

	private int updateExtrasDetails(SorecardExtrasDetails extrasDetails) {
		String sql = "UPDATE scorecard_extras_details SET legbyes=? ,byes=? ,wides=? ,noballs=?, total=?  WHERE "
				+ "game_id =? and innings_id = ?";
		int rows = jdbcTemplate.update(sql, extrasDetails.getLegbyes(), extrasDetails.getByes(),
				extrasDetails.getWides(), extrasDetails.getNoballs(), extrasDetails.getTotal(),
				extrasDetails.getGame_id(), extrasDetails.getInnings_id());
		logger.info("rows are ::" + rows);
		if (rows > 1) {
			logger.warn("More than one Rows updated");
		}

		return rows;
	}

}
