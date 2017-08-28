package com.cricket.austin.zulfi.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScorecardGameDetails {
	@JsonProperty("game_id")
	private int		gameId;

	@JsonProperty("league_id")
	private int		leagueId;

	@JsonProperty("season")
	private int		season;

	@JsonProperty("week")
	private int		week;

	@JsonProperty("awayteam")
	private int		awayteam;

	@JsonProperty("hometeam")
	private int		hometeam;

	@JsonProperty("umpireTeam")
	private int		umpires;
	// TODO:umpireTeam

	@JsonProperty("toss_won_id")
	private int		tossWonId;

	@JsonProperty("result_won_id")
	private int		resultWonId;

	@JsonProperty("batting_first_id")
	private int		battingFirstId;

	@JsonProperty("batting_second_id")
	private int		battingSecondId;

	@JsonProperty("ground_id")
	private int		groundId;

	@JsonProperty("ground_name")
	private String	groundName;

	@JsonProperty("game_date")
	private String	gameDate;

	@JsonProperty("result")
	private String	result;
	@JsonProperty("tied")
	private int		tied;
	@JsonProperty("mom")
	private int		mom;

	@JsonProperty("umpire1")
	private int		umpire1;

	@JsonProperty("umpire2")
	private int		umpire2;

	@JsonProperty("maxovers")
	private int		maxovers;

	@JsonProperty("forfeit")
	private int		forfeit;

	@JsonProperty("cancelled")
	private int		cancelled;

	@JsonProperty("cancelledplay")
	private int		cancelledplay;

	@JsonProperty("isactive")
	private int		isactive;

	@JsonProperty("report")
	private String	report;

	@JsonProperty("points")
	private String	points;

	@JsonProperty("completed")
	private int		completed;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getAwayteam() {
		return awayteam;
	}

	public void setAwayteam(int awayteam) {
		this.awayteam = awayteam;
	}

	public int getHometeam() {
		return hometeam;
	}

	public void setHometeam(int hometeam) {
		this.hometeam = hometeam;
	}

	public int getUmpires() {
		return umpires;
	}

	public void setUmpires(int umpires) {
		this.umpires = umpires;
	}

	public int getTossWonId() {
		return tossWonId;
	}

	public void setTossWonId(int tossWonId) {
		this.tossWonId = tossWonId;
	}

	public int getResultWonId() {
		return resultWonId;
	}

	public void setResultWonId(int resultWonId) {
		this.resultWonId = resultWonId;
	}

	public int getBattingFirstId() {
		return battingFirstId;
	}

	public void setBattingFirstId(int battingFirstId) {
		this.battingFirstId = battingFirstId;
	}

	public int getBattingSecondId() {
		return battingSecondId;
	}

	public void setBattingSecondId(int battingSecondId) {
		this.battingSecondId = battingSecondId;
	}

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}

	public String getGameDate() {
		return gameDate;
	}

	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTied() {
		return tied;
	}

	public void setTied(int tied) {
		this.tied = tied;
	}

	public int getMom() {
		return mom;
	}

	public void setMom(int mom) {
		this.mom = mom;
	}

	public int getUmpire1() {
		return umpire1;
	}

	public void setUmpire1(int umpire1) {
		this.umpire1 = umpire1;
	}

	public int getUmpire2() {
		return umpire2;
	}

	public void setUmpire2(int umpire2) {
		this.umpire2 = umpire2;
	}

	public int getMaxovers() {
		return maxovers;
	}

	public void setMaxovers(int maxovers) {
		this.maxovers = maxovers;
	}

	public int getForfeit() {
		return forfeit;
	}

	public void setForfeit(int forfeit) {
		this.forfeit = forfeit;
	}

	public int getCancelled() {
		return cancelled;
	}

	public void setCancelled(int cancelled) {
		this.cancelled = cancelled;
	}

	public int getCancelledplay() {
		return cancelledplay;
	}

	public void setCancelledplay(int cancelledplay) {
		this.cancelledplay = cancelledplay;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	@Override
	public String toString() {
		return "ScorecardGameDetails [gameId=" + gameId + ", leagueId=" + leagueId + ", season=" + season + ", week="
				+ week + ", awayteam=" + awayteam + ", hometeam=" + hometeam + ", umpires=" + umpires + ", tossWonId="
				+ tossWonId + ", resultWonId=" + resultWonId + ", battingFirstId=" + battingFirstId
				+ ", battingSecondId=" + battingSecondId + ", groundId=" + groundId + ", groundName=" + groundName
				+ ", gameDate=" + gameDate + ", result=" + result + ", tied=" + tied + ", mom=" + mom + ", umpire1="
				+ umpire1 + ", umpire2=" + umpire2 + ", maxovers=" + maxovers + ", forfeit=" + forfeit + ", cancelled="
				+ cancelled + ", cancelledplay=" + cancelledplay + ", isactive=" + isactive + ", report=" + report
				+ ", points=" + points + ", completed=" + completed + "]";
	}

}
