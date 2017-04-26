package com.zulfi.springmvc.dao;

import java.util.List;

import com.zulfi.springmvc.model.Ladder;
import com.zulfi.springmvc.model.ScoreCardBasic;

public interface TeamDao {

	List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

	List<ScoreCardBasic> getbasicScoreCard(int seasonId);

}
