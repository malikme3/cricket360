package com.zulfi.springmvc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seasons", schema = "world")
public class Seasons implements Serializable {

	@Id
	@GeneratedValue
	int SeasonID;
	String SeasonName;
	String SeasonYear;
	int SeasonLeague;

	public int getSeasonID() {
		return SeasonID;
	}

	public void setSeasonID(int seasonID) {
		SeasonID = seasonID;
	}

	public String getSeasonName() {
		return SeasonName;
	}

	public void setSeasonName(String seasonName) {
		SeasonName = seasonName;
	}

	public String getSeasonYear() {
		return SeasonYear;
	}

	public void setSeasonYear(String seasonYear) {
		SeasonYear = seasonYear;
	}

	public int getSeasonLeague() {
		return SeasonLeague;
	}

	public void setSeasonLeague(int seasonLeague) {
		SeasonLeague = seasonLeague;
	}

	@Override
	public String toString() {
		return "Seasons [SeasonID=" + SeasonID + ", SeasonName=" + SeasonName + ", SeasonYear=" + SeasonYear + ", SeasonLeague=" + SeasonLeague + "]";
	}
}
