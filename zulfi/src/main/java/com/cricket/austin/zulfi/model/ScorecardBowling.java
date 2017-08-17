package com.cricket.austin.zulfi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBowling {
	private int						game_id;
	private int						season;
	private int						innings_id;
	private int						team;
	private int						opponent;
	private ScorecardBowlingDetails	bowler_1;
	private ScorecardBowlingDetails	bowler_2;
	private ScorecardBowlingDetails	bowler_3;
	private ScorecardBowlingDetails	bowler_4;
	private ScorecardBowlingDetails	bowler_5;
	private ScorecardBowlingDetails	bowler_6;
	private ScorecardBowlingDetails	bowler_7;
	private ScorecardBowlingDetails	bowler_8;
	private ScorecardBowlingDetails	bowler_9;
	private ScorecardBowlingDetails	bowler_10;
	private ScorecardBowlingDetails	bowler_11;

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
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

	public int getOpponent() {
		return opponent;
	}

	public void setOpponent(int opponent) {
		this.opponent = opponent;
	}

	public ScorecardBowlingDetails getBowler_1() {
		return bowler_1;
	}

	public void setBowler_1(ScorecardBowlingDetails bowler_1) {
		this.bowler_1 = bowler_1;
	}

	public ScorecardBowlingDetails getBowler_2() {
		return bowler_2;
	}

	public void setBowler_2(ScorecardBowlingDetails bowler_2) {
		this.bowler_2 = bowler_2;
	}

	public ScorecardBowlingDetails getBowler_3() {
		return bowler_3;
	}

	public void setBowler_3(ScorecardBowlingDetails bowler_3) {
		this.bowler_3 = bowler_3;
	}

	public ScorecardBowlingDetails getBowler_4() {
		return bowler_4;
	}

	public void setBowler_4(ScorecardBowlingDetails bowler_4) {
		this.bowler_4 = bowler_4;
	}

	public ScorecardBowlingDetails getBowler_5() {
		return bowler_5;
	}

	public void setBowler_5(ScorecardBowlingDetails bowler_5) {
		this.bowler_5 = bowler_5;
	}

	public ScorecardBowlingDetails getBowler_6() {
		return bowler_6;
	}

	public void setBowler_6(ScorecardBowlingDetails bowler_6) {
		this.bowler_6 = bowler_6;
	}

	public ScorecardBowlingDetails getBowler_7() {
		return bowler_7;
	}

	public void setBowler_7(ScorecardBowlingDetails bowler_7) {
		this.bowler_7 = bowler_7;
	}

	public ScorecardBowlingDetails getBowler_8() {
		return bowler_8;
	}

	public void setBowler_8(ScorecardBowlingDetails bowler_8) {
		this.bowler_8 = bowler_8;
	}

	public ScorecardBowlingDetails getBowler_9() {
		return bowler_9;
	}

	public void setBowler_9(ScorecardBowlingDetails bowler_9) {
		this.bowler_9 = bowler_9;
	}

	public ScorecardBowlingDetails getBowler_10() {
		return bowler_10;
	}

	public void setBowler_10(ScorecardBowlingDetails bowler_10) {
		this.bowler_10 = bowler_10;
	}

	public ScorecardBowlingDetails getBowler_11() {
		return bowler_11;
	}

	public void setBowler_11(ScorecardBowlingDetails bowler_11) {
		this.bowler_11 = bowler_11;
	}

	@Override
	public String toString() {
		return "ScorecardBowling [game_id=" + game_id + ", season=" + season + ", innings_id=" + innings_id + ", team="
				+ team + ", opponent=" + opponent + ", bowler_1=" + bowler_1 + ", bowler_2=" + bowler_2 + ", bowler_3="
				+ bowler_3 + ", bowler_4=" + bowler_4 + ", bowler_5=" + bowler_5 + ", bowler_6=" + bowler_6
				+ ", bowler_7=" + bowler_7 + ", bowler_8=" + bowler_8 + ", bowler_9=" + bowler_9 + ", bowler_10="
				+ bowler_10 + ", bowler_11=" + bowler_11 + "]";
	}

}
