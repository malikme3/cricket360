package com.zulfi.springmvc.service;

import java.util.List;

import com.zulfi.springmvc.model.Ladder;

public interface TeamService {

	public List<Ladder> getTeamPosition(String seasonYear, String seasonName);

	public List<Ladder> getTeamsIdTeamsAbbrv(String seasonYear, String seasonName);

}