package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams", schema = "world")
public class Teams implements Serializable {

	@Id
	@GeneratedValue
	int TeamID;
	int ClubID;
	int LeagueID;
	String TeamName;
	String TeamAbbrev;
	String TeamURL;
	String TeamColour;
	String picture;
	String TeamActive;
	String TeamDesc;
	String TeamGroup;

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	public int getClubID() {
		return ClubID;
	}

	public void setClubID(int clubID) {
		ClubID = clubID;
	}

	public int getLeagueID() {
		return LeagueID;
	}

	public void setLeagueID(int leagueID) {
		LeagueID = leagueID;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public String getTeamAbbrev() {
		return TeamAbbrev;
	}

	public void setTeamAbbrev(String teamAbbrev) {
		TeamAbbrev = teamAbbrev;
	}

	public String getTeamURL() {
		return TeamURL;
	}

	public void setTeamURL(String teamURL) {
		TeamURL = teamURL;
	}

	public String getTeamColour() {
		return TeamColour;
	}

	public void setTeamColour(String teamColour) {
		TeamColour = teamColour;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTeamActive() {
		return TeamActive;
	}

	public void setTeamActive(String teamActive) {
		TeamActive = teamActive;
	}

	public String getTeamDesc() {
		return TeamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		TeamDesc = teamDesc;
	}

	public String getTeamGroup() {
		return TeamGroup;
	}

	public void setTeamGroup(String teamGroup) {
		TeamGroup = teamGroup;
	}

	@Override
	public String toString() {
		return "Teams [TeamID=" + TeamID + ", ClubID=" + ClubID + ", LeagueID=" + LeagueID + ", TeamName=" + TeamName + ", TeamAbbrev=" + TeamAbbrev + ", TeamURL=" + TeamURL + ", TeamColour="
				+ TeamColour + ", picture=" + picture + ", TeamActive=" + TeamActive + ", TeamDesc=" + TeamDesc + ", TeamGroup=" + TeamGroup + "]";
	}

}
