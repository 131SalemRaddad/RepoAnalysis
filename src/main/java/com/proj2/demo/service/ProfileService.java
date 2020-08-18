package com.proj2.demo.service;

import java.util.List;

import com.proj2.demo.model.Profile;

public interface ProfileService {
	// get all profiles
	public List<Profile> getAllProfiles();
	
	// get a profile by id
	public Profile getProfileById(int id);
	
	// add a new profile
	public String saveProfile(Profile profile);
	
	// add a new list of profiles
	public String saveAllProfiles(List<Profile> profileList);
	
	// delete a profile by id
	public Profile deleteProfileById(int id);
	
	// update an existed profile
	public Profile updateProfile(Profile profile);
	
	// searching for profiles that uses XDE
	public List<Profile> findProfilesUsesXDE();
	
	// add a job
	public String addJob(int profileId, String uuid);
}