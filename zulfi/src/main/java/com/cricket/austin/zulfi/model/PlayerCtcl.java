package com.cricket.austin.zulfi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players", schema = "world")
public class PlayerCtcl {

	@Id
	@GeneratedValue
	private int PlayerID;
	private String PlayerFName;
	private String PlayerLName;
	private String PlayerLAbbrev;
	private int PlayerClub;
	private int PlayerTeam;
	private String PlayerEmail;
	private int IsUmpire;
	private int IsPresident;
	private int IsVicePresident;
	private int IsSecretary;
	private int IsTreasurer;
	private int IsCaptain;
	private int IsViceCaptain;
	private String Born;
	private String BattingStyle;
	private String BowlingStyle;
	private String shortprofile;
	private String AIM;
	private String YAHOO;
	private String MSN;
	private String ICQ;
	private String picture;
	private String picture1;
	private int isactive;
	private String playerAvailability;

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}

	public String getPlayerFName() {
		return PlayerFName;
	}

	public void setPlayerFName(String playerFName) {
		PlayerFName = playerFName;
	}

	public String getPlayerLName() {
		return PlayerLName;
	}

	public void setPlayerLName(String playerLName) {
		PlayerLName = playerLName;
	}

	public String getPlayerLAbbrev() {
		return PlayerLAbbrev;
	}

	public void setPlayerLAbbrev(String playerLAbbrev) {
		PlayerLAbbrev = playerLAbbrev;
	}

	public int getPlayerClub() {
		return PlayerClub;
	}

	public void setPlayerClub(int playerClub) {
		PlayerClub = playerClub;
	}

	public int getPlayerTeam() {
		return PlayerTeam;
	}

	public void setPlayerTeam(int playerTeam) {
		PlayerTeam = playerTeam;
	}

	public String getPlayerEmail() {
		return PlayerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		PlayerEmail = playerEmail;
	}

	public int getIsUmpire() {
		return IsUmpire;
	}

	public void setIsUmpire(int isUmpire) {
		IsUmpire = isUmpire;
	}

	public int getIsPresident() {
		return IsPresident;
	}

	public void setIsPresident(int isPresident) {
		IsPresident = isPresident;
	}

	public int getIsVicePresident() {
		return IsVicePresident;
	}

	public void setIsVicePresident(int isVicePresident) {
		IsVicePresident = isVicePresident;
	}

	public int getIsSecretary() {
		return IsSecretary;
	}

	public void setIsSecretary(int isSecretary) {
		IsSecretary = isSecretary;
	}

	public int getIsTreasurer() {
		return IsTreasurer;
	}

	public void setIsTreasurer(int isTreasurer) {
		IsTreasurer = isTreasurer;
	}

	public int getIsCaptain() {
		return IsCaptain;
	}

	public void setIsCaptain(int isCaptain) {
		IsCaptain = isCaptain;
	}

	public int getIsViceCaptain() {
		return IsViceCaptain;
	}

	public void setIsViceCaptain(int isViceCaptain) {
		IsViceCaptain = isViceCaptain;
	}

	public String getBorn() {
		return Born;
	}

	public void setBorn(String born) {
		Born = born;
	}

	public String getBattingStyle() {
		return BattingStyle;
	}

	public void setBattingStyle(String battingStyle) {
		BattingStyle = battingStyle;
	}

	public String getBowlingStyle() {
		return BowlingStyle;
	}

	public void setBowlingStyle(String bowlingStyle) {
		BowlingStyle = bowlingStyle;
	}

	public String getShortprofile() {
		return shortprofile;
	}

	public void setShortprofile(String shortprofile) {
		this.shortprofile = shortprofile;
	}

	public String getAIM() {
		return AIM;
	}

	public void setAIM(String aIM) {
		AIM = aIM;
	}

	public String getYAHOO() {
		return YAHOO;
	}

	public void setYAHOO(String yAHOO) {
		YAHOO = yAHOO;
	}

	public String getMSN() {
		return MSN;
	}

	public void setMSN(String mSN) {
		MSN = mSN;
	}

	public String getICQ() {
		return ICQ;
	}

	public void setICQ(String iCQ) {
		ICQ = iCQ;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicture1() {
		return picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getPlayerAvailability() {
		return playerAvailability;
	}

	public void setPlayerAvailability(String playerAvailability) {
		this.playerAvailability = playerAvailability;
	}

	@Override
	public String toString() {
		return "PlayerCtcl [PlayerID=" + PlayerID + ", PlayerFName=" + PlayerFName + ", PlayerLName=" + PlayerLName + ", PlayerLAbbrev=" + PlayerLAbbrev + ", PlayerClub=" + PlayerClub
				+ ", PlayerTeam=" + PlayerTeam + ", PlayerEmail=" + PlayerEmail + ", IsUmpire=" + IsUmpire + ", IsPresident=" + IsPresident + ", IsVicePresident=" + IsVicePresident + ", IsSecretary="
				+ IsSecretary + ", IsTreasurer=" + IsTreasurer + ", IsCaptain=" + IsCaptain + ", IsViceCaptain=" + IsViceCaptain + ", Born=" + Born + ", BattingStyle=" + BattingStyle
				+ ", BowlingStyle=" + BowlingStyle + ", shortprofile=" + shortprofile + ", AIM=" + AIM + ", YAHOO=" + YAHOO + ", MSN=" + MSN + ", ICQ=" + ICQ + ", picture=" + picture + ", picture1="
				+ picture1 + ", isactive=" + isactive + ", playerAvailability=" + playerAvailability + "]";
	}

}
