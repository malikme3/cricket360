package com.cricket.austin.zulfi.model;

public class SorecardExtrasDetails {
	private int	game_id;
	private int	innings_id;
	private int	legbyes;
	private int	byes;
	private int	wides;
	private int	noballs;
	private int	total;

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

	public int getLegbyes() {
		return legbyes;
	}

	public void setLegbyes(int legbyes) {
		this.legbyes = legbyes;
	}

	public int getByes() {
		return byes;
	}

	public void setByes(int byes) {
		this.byes = byes;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getNoballs() {
		return noballs;
	}

	public void setNoballs(int noballs) {
		this.noballs = noballs;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SorecardExtrasDetails [game_id=" + game_id + ", innings_id=" + innings_id + ", legbyes=" + legbyes
				+ ", byes=" + byes + ", wides=" + wides + ", noballs=" + noballs + ", total=" + total + "]";
	}

}
