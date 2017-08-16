package com.cricket.austin.zulfi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBatting {
	private int								name;
	private int								game_id;
	private int								season;
	private int								innings_id;
	private int								team;
	private int								opponent;
	private List<ScorecardBattingDetails>	listBatsDetails;

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

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

	public List<ScorecardBattingDetails> getListBatsDetails() {
		return listBatsDetails;
	}

	public void setListBatsDetails(List<ScorecardBattingDetails> listBatsDetails) {
		this.listBatsDetails = listBatsDetails;
	}

	@Override
	public String toString() {
		return "ScorecardBatting [name=" + name + ", game_id=" + game_id + ", season=" + season + ", innings_id="
				+ innings_id + ", team=" + team + ", opponent=" + opponent + ", listBatsDetails=" + listBatsDetails
				+ "]";
	}

}
