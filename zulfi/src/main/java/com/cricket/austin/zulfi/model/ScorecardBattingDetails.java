package com.cricket.austin.zulfi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScorecardBattingDetails {
	private int	player_id;
	private int	batting_position;
	private int	how_out;
	private int	runs;
	private int	assist;
	private int	bowler;
	private int	balls;
	private int	fours;
	private int	sixes;
	private int	notout;

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getBatting_position() {
		return batting_position;
	}

	public void setBatting_position(int batting_position) {
		this.batting_position = batting_position;
	}

	public int getHow_out() {
		return how_out;
	}

	public void setHow_out(int how_out) {
		this.how_out = how_out;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getBowler() {
		return bowler;
	}

	public void setBowler(int bowler) {
		this.bowler = bowler;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

	public int getNotout() {
		return notout;
	}

	public void setNotout(int notout) {
		this.notout = notout;
	}

	@Override
	public String toString() {
		return "ScorecardBattingDetails [player_id=" + player_id + ", batting_position=" + batting_position
				+ ", how_out=" + how_out + ", runs=" + runs + ", assist=" + assist + ", bowler=" + bowler + ", balls="
				+ balls + ", fours=" + fours + ", sixes=" + sixes + ", notout=" + notout + "]";
	}

}
