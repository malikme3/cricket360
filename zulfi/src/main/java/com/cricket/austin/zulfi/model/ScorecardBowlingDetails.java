package com.cricket.austin.zulfi.model;

public class ScorecardBowlingDetails {
	private int	player_id;
	private int	bowling_position;
	private int	overs;
	private int	maidens;
	private int	runs;
	private int	wickets;
	private int	noballs;
	private int	wides;

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

	@Override
	public String toString() {
		return "ScorecardBowlingDetails [player_id=" + player_id + ", bowling_position=" + bowling_position + ", overs="
				+ overs + ", maidens=" + maidens + ", runs=" + runs + ", wickets=" + wickets + ", noballs=" + noballs
				+ ", wides=" + wides + "]";
	}

}
