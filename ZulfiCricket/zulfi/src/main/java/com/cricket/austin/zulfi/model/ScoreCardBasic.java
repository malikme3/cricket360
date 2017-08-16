package com.cricket.austin.zulfi.model;

import java.util.Date;

public class ScoreCardBasic {

	private int		game_id;
	private Date	match_date;
	private String	host_team;
	private String	guest_team;
	private String	player_first_name;
	private String	player_last_name;
	private String	man_of_the_match;
	private String	match_status;

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public Date getMatch_date() {
		return match_date;
	}

	public void setMatch_date(Date match_date) {
		this.match_date = match_date;
	}

	public String getHost_team() {
		return host_team;
	}

	public void setHost_team(String host_team) {
		this.host_team = host_team;
	}

	public String getGuest_team() {
		return guest_team;
	}

	public void setGuest_team(String guest_team) {
		this.guest_team = guest_team;
	}

	public String getMan_of_the_match() {
		return man_of_the_match;
	}

	public void setMan_of_the_match(String man_of_the_match) {
		this.man_of_the_match = man_of_the_match;
	}

	public String getMatch_status() {
		return match_status;
	}

	public void setMatch_status(String match_status) {
		this.match_status = match_status;
	}

	public String getPlayer_last_name() {
		return player_last_name;
	}

	public void setPlayer_last_name(String player_last_name) {
		this.player_last_name = player_last_name;
	}

	public String getPlayer_first_name() {
		return player_first_name;
	}

	public void setPlayer_first_name(String player_first_name) {
		this.player_first_name = player_first_name;
	}

	@Override
	public String toString() {
		return "ScoreCardBasic [game_id=" + game_id + ", match_date=" + match_date + ", host_team=" + host_team
				+ ", guest_team=" + guest_team + ", player_first_name=" + player_first_name + ", player_last_name="
				+ player_last_name + ", man_of_the_match=" + man_of_the_match + ", match_status=" + match_status + "]";
	}

}
