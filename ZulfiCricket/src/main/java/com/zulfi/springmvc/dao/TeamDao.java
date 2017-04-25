package com.zulfi.springmvc.dao;

import java.util.List;

import com.zulfi.springmvc.model.Ladder;

public interface TeamDao {

	List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

}
