package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaguemanagement", schema = "world")
public class Leagues implements Serializable {

	@Id
	@GeneratedValue
	int LeagueID;
	String LeagueName;

	public int getLeagueID() {
		return LeagueID;
	}

	public void setLeagueID(int leagueID) {
		LeagueID = leagueID;
	}

	public String getLeagueName() {
		return LeagueName;
	}

	public void setLeagueName(String leagueName) {
		LeagueName = leagueName;
	}

	public String getLeagueAbbrev() {
		return LeagueAbbrev;
	}

	public void setLeagueAbbrev(String leagueAbbrev) {
		LeagueAbbrev = leagueAbbrev;
	}

	@Override
	public String toString() {
		return "Leagues [LeagueID=" + LeagueID + ", LeagueName=" + LeagueName + ", LeagueAbbrev=" + LeagueAbbrev + "]";
	}

	String LeagueAbbrev;
}
