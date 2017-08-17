package com.cricket.austin.zulfi.model;

import java.io.Serializable;

public class PointsTable implements Serializable {

	int team;
	int won;
	int points;

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "PointsTable [team=" + team + ", won=" + won + ", points=" + points + "]";
	}

}
