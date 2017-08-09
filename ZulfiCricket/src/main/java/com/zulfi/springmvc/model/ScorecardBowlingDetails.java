package com.zulfi.springmvc.model;

public class ScorecardBowlingDetails {
	private int	game_id;
	private int	innings_id;
	private int	player_id;
	private int	bowling_position;
	private int	overs;
	private int	maidens;
	private int	runs;
	private int	wickets;
	private int	noballs;
	private int	wides;
	private int	season;
	private int	team;
	private int	opponent;

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getInnings_id() {
		return innings_id;
	}

	public void setInnings_id(int innings_id) {
		this.innings_id = innings_id;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getBowling_position() {
		return bowling_position;
	}

	public void setBowling_position(int bowling_position) {
		this.bowling_position = bowling_position;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public int getMaidens() {
		return maidens;
	}

	public void setMaidens(int maidens) {
		this.maidens = maidens;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getNoballs() {
		return noballs;
	}

	public void setNoballs(int noballs) {
		this.noballs = noballs;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getOpponent() {
		return opponent;
	}

	public void setOpponent(int opponent) {
		this.opponent = opponent;
	}

	@Override
	public String toString() {
		return "ScorecardBowlingDetails [game_id=" + game_id + ", innings_id=" + innings_id + ", player_id=" + player_id
				+ ", bowling_position=" + bowling_position + ", overs=" + overs + ", maidens=" + maidens + ", runs="
				+ runs + ", wickets=" + wickets + ", noballs=" + noballs + ", wides=" + wides + ", season=" + season
				+ ", team=" + team + ", opponent=" + opponent + "]";
	}

}
