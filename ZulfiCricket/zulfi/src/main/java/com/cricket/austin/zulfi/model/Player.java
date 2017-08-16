package com.cricket.austin.zulfi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cricket.players")
public class Player {

	@Id
	@GeneratedValue
	private int player_id;

	@Column(name = "player_firstName")
	private String player_firstName;

	@Column(name = "player_lastName")
	private String player_lastName;

	@Column(name = "player_email")
	private String player_email;

	@Column(name = "player_phone")
	private String player_phone;

	@Column(name = "player_address")
	private String player_address;

	@Column(name = "player_skills")
	private String player_skills;

	@Column(name = "player_team")
	private String player_team;

	@Column(name = "player_club")
	private String player_club;

	@Column(name = "player_availability")
	private String player_availability;


	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_firstName() {
		return player_firstName;
	}

	public void setPlayer_firstName(String player_firstName) {
		this.player_firstName = player_firstName;
	}

	public String getPlayer_team() {
		return player_team;
	}

	public void setPlayer_team(String player_team) {
		this.player_team = player_team;
	}

	public String getPlayer_club() {
		return player_club;
	}

	public void setPlayer_club(String player_club) {
		this.player_club = player_club;
	}

	public String getPlayer_email() {
		return player_email;
	}

	public void setPlayer_email(String player_email) {
		this.player_email = player_email;
	}

	public String getPlayer_phone() {
		return player_phone;
	}

	public void setPlayer_phone(String player_phone) {
		this.player_phone = player_phone;
	}

	public String getPlayer_address() {
		return player_address;
	}

	public void setPlayer_address(String player_address) {
		this.player_address = player_address;
	}

	public String getPlayer_skills() {
		return player_skills;
	}

	public void setPlayer_skills(String player_skills) {
		this.player_skills = player_skills;
	}

	public String getPlayer_lastName() {
		return player_lastName;
	}

	public void setPlayer_lastName(String player_lastName) {
		this.player_lastName = player_lastName;
	}

	public String getPlayer_availability() {
		return player_availability;
	}

	public void setPlayer_availability(String player_availability) {
		this.player_availability = player_availability;
	}

	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", player_firstName=" + player_firstName + ", player_lastName=" + player_lastName + ", player_email=" + player_email + ", player_phone="
				+ player_phone + ", player_address=" + player_address + ", player_skills=" + player_skills + ", player_team=" + player_team + ", player_club=" + player_club + ", player_availability="
				+ player_availability + "]";
	}

}
