package com.cricket.austin.zulfi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBatting {
	private int						game_id;
	private int						season;
	private int						innings_id;
	private int						team;
	private int						opponent;
	private ScorecardBattingDetails	batsman_1;
	private ScorecardBattingDetails	batsman_2;
	private ScorecardBattingDetails	batsman_3;
	private ScorecardBattingDetails	batsman_4;
	private ScorecardBattingDetails	batsman_5;
	private ScorecardBattingDetails	batsman_6;
	private ScorecardBattingDetails	batsman_7;
	private ScorecardBattingDetails	batsman_8;
	private ScorecardBattingDetails	batsman_9;
	private ScorecardBattingDetails	batsman_10;
	private ScorecardBattingDetails	batsman_11;

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

	public ScorecardBattingDetails getBatsman_1() {
		return batsman_1;
	}

	public void setBatsman_1(ScorecardBattingDetails batsman_1) {
		this.batsman_1 = batsman_1;
	}

	public ScorecardBattingDetails getBatsman_2() {
		return batsman_2;
	}

	public void setBatsman_2(ScorecardBattingDetails batsman_2) {
		this.batsman_2 = batsman_2;
	}

	public ScorecardBattingDetails getBatsman_3() {
		return batsman_3;
	}

	public void setBatsman_3(ScorecardBattingDetails batsman_3) {
		this.batsman_3 = batsman_3;
	}

	public ScorecardBattingDetails getBatsman_4() {
		return batsman_4;
	}

	public void setBatsman_4(ScorecardBattingDetails batsman_4) {
		this.batsman_4 = batsman_4;
	}

	public ScorecardBattingDetails getBatsman_5() {
		return batsman_5;
	}

	public void setBatsman_5(ScorecardBattingDetails batsman_5) {
		this.batsman_5 = batsman_5;
	}

	public ScorecardBattingDetails getBatsman_6() {
		return batsman_6;
	}

	public void setBatsman_6(ScorecardBattingDetails batsman_6) {
		this.batsman_6 = batsman_6;
	}

	public ScorecardBattingDetails getBatsman_7() {
		return batsman_7;
	}

	public void setBatsman_7(ScorecardBattingDetails batsman_7) {
		this.batsman_7 = batsman_7;
	}

	public ScorecardBattingDetails getBatsman_8() {
		return batsman_8;
	}

	public void setBatsman_8(ScorecardBattingDetails batsman_8) {
		this.batsman_8 = batsman_8;
	}

	public ScorecardBattingDetails getBatsman_9() {
		return batsman_9;
	}

	public void setBatsman_9(ScorecardBattingDetails batsman_9) {
		this.batsman_9 = batsman_9;
	}

	public ScorecardBattingDetails getBatsman_10() {
		return batsman_10;
	}

	public void setBatsman_10(ScorecardBattingDetails batsman_10) {
		this.batsman_10 = batsman_10;
	}

	public ScorecardBattingDetails getBatsman_11() {
		return batsman_11;
	}

	public void setBatsman_11(ScorecardBattingDetails batsman_11) {
		this.batsman_11 = batsman_11;
	}

	@Override
	public String toString() {
		return "ScorecardBatting [game_id=" + game_id + ", season=" + season + ", innings_id=" + innings_id + ", team="
				+ team + ", opponent=" + opponent + ", batsman_1=" + batsman_1 + ", batsman_2=" + batsman_2
				+ ", batsman_3=" + batsman_3 + ", batsman_4=" + batsman_4 + ", batsman_5=" + batsman_5 + ", batsman_6="
				+ batsman_6 + ", batsman_7=" + batsman_7 + ", batsman_8=" + batsman_8 + ", batsman_9=" + batsman_9
				+ ", batsman_10=" + batsman_10 + ", batsman_11=" + batsman_11 + "]";
	}

}
