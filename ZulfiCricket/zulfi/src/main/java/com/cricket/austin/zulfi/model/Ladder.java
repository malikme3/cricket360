package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "world.ladder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ladder implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "season")
	private int season;
	private int conference;
	private int team;
	private int played;
	private int won;
	private int tied;
	private int lost;
	private double nrr;
	private int points;
	private int penalty;
	private int bonus;
	private int totalpoints;
	private int manualrank;
	@Transient
	private String teamName;
	@Transient
	private String conferenceAbbrev;
	@Transient
	private String teamAbbrev;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getConference() {
		return conference;
	}

	public void setConference(int conference) {
		this.conference = conference;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
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

	public int getTied() {
		return tied;
	}

	public void setTied(int tied) {
		this.tied = tied;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public double getNrr() {
		return nrr;
	}

	public void setNrr(double nrr) {
		this.nrr = nrr;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}

	public int getManualrank() {
		return manualrank;
	}

	public void setManualrank(int manualrank) {
		this.manualrank = manualrank;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getConferenceAbbrev() {
		return conferenceAbbrev;
	}

	public void setConferenceAbbrev(String conferenceAbbrev) {
		this.conferenceAbbrev = conferenceAbbrev;
	}

	public String getTeamAbbrev() {
		return teamAbbrev;
	}

	public void setTeamAbbrev(String teamAbbrev) {
		this.teamAbbrev = teamAbbrev;
	}

	@Override
	public String toString() {
		return "Ladder [id=" + id + ", season=" + season + ", conference=" + conference + ", team=" + team + ", played=" + played + ", won=" + won + ", tied=" + tied + ", lost=" + lost + ", nrr="
				+ nrr + ", points=" + points + ", penalty=" + penalty + ", bonus=" + bonus + ", totalpoints=" + totalpoints + ", manualrank=" + manualrank + ", teamName=" + teamName
				+ ", conferenceAbbrev=" + conferenceAbbrev + ", teamAbbrev=" + teamAbbrev + "]";
	}

}
