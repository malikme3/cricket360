package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams", schema = "world")
public class SubmitResults implements Serializable {

	@Id
	@GeneratedValue
	int	TeamID;
	int	played;
	int	won;
	int	lost;
	int	tied;
	int	nr;
	public int getTeamID() {
		return TeamID;
	}
	public void setTeamID(int teamID) {
		TeamID = teamID;
	}
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public int getTied() {
		return tied;
	}
	public void setTied(int tied) {
		this.tied = tied;
	}
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	
	@Override
	public String toString() {
		return "Teams [TeamID=" + TeamID + ", played=" + played + ", won=" + won + ", lost=" + lost + ", tied=" + tied
				+ ", nr=" + nr + "]";
	}	

}
