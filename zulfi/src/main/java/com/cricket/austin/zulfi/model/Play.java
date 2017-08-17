package com.cricket.austin.zulfi.model;

import java.io.Serializable;

public class Play implements Serializable {

	private String player_1;
	private String player_2;

	public String getPlayer_1() {
		return player_1;
	}

	public void setPlayer_1(String player_1) {
		this.player_1 = player_1;
	}

	public String getPlayer_2() {
		return player_2;
	}

	public void setPlayer_2(String player_2) {
		this.player_2 = player_2;
	}

	@Override
	public String toString() {
		return "Play [player_1=" + player_1 + ", player_2=" + player_2 + "]";
	}

}
