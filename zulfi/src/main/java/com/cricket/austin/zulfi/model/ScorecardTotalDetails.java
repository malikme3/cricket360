package com.cricket.austin.zulfi.model;

public class ScorecardTotalDetails {
	private int		game_id;
	private int		innings_id;
	private int		team;
	private int		wickets;
	private int		total;
	private float	overs;

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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public float getOvers() {
		return overs;
	}

	public void setOvers(float overs) {
		this.overs = overs;
	}

	@Override
	public String toString() {
		return "ScorecardTotalDetails [game_id=" + game_id + ", innings_id=" + innings_id + ", team=" + team
				+ ", wickets=" + wickets + ", total=" + total + ", overs=" + overs + "]";
	}

}
