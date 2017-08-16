package com.cricket.austin.zulfi.model;

import java.util.Date;

public class Schedule {
	
	private int week;
	private int seasonId;
	private Date date;
	private String seasonName;
	private String awayTeam;
	private String homeTeam;
	private String umpireFName;
	private String umpireLName;
	private String ground;
	private String formattedDate;
	
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getUmpireFName() {
		return umpireFName;
	}
	public void setUmpireFName(String umpireFName) {
		this.umpireFName = umpireFName;
	}
	public String getUmpireLName() {
		return umpireLName;
	}
	public void setUmpireLName(String umpireLName) {
		this.umpireLName = umpireLName;
	}
	
	public String getGround() {
		return ground;
	}
	public void setGround(String ground) {
		this.ground = ground;
	}
	
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	@Override
	public String toString() {
		return "Schedule [week=" + week + ", seasonId=" + seasonId + ", date=" + date + ", seasonName=" + seasonName + ", awayTeam=" + awayTeam + ", homeTeam=" + homeTeam + ", umpireFName="
				+ umpireFName + ", umpireLName=" + umpireLName + ", ground=" + ground + ", formattedDate=" + formattedDate + "]";
	}

}
