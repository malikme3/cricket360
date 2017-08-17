package com.cricket.austin.zulfi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "seasons", schema = "world")
public class Seasons implements Serializable {

	@Id
	@GeneratedValue
	private int SeasonID;
	private String SeasonName;
	private String SeasonYear;
	public int SeasonLeague;

	@JsonIgnoreProperties(value = "groupCategory")
	public String groupCategory;

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

	public String getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}

	@Override
	public String toString() {
		return "Seasons [SeasonID=" + SeasonID + ", SeasonName=" + SeasonName + ", SeasonYear=" + SeasonYear + ", SeasonLeague=" + SeasonLeague + ", groupCategory=" + groupCategory + "]";
	}

}
