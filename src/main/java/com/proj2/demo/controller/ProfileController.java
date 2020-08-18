package com.proj2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj2.demo.model.Profile;
import com.proj2.demo.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private ProfileService profileServiceBean;
	
	@GetMapping("/getAll")
	public List<Profile> getAllProfiles(){
		return profileServiceBean.getAllProfiles();
	}
	
	@GetMapping("/get/{id}")
	public Profile getProfileById(@PathVariable("id")int id) {
		return profileServiceBean.getProfileById(id);
	}
	
	@GetMapping("/getXDEUsers")
	public List<Profile> getProfilesUsesXDE(){
		return profileServiceBean.findProfilesUsesXDE();
	}
	
	@PostMapping("/add")
	public String saveProfile(@RequestBody Profile profile) {
		return profileServiceBean.saveProfile(profile);
	}
	
	@PostMapping("/addAll")
	public String saveAllProfiles(@RequestBody List<Profile> profileList) {
		return profileServiceBean.saveAllProfiles(profileList);
	}
	
	@PostMapping("/addJob/{profileId}/{uuid}")
	public String saveJob(@PathVariable("profileId")int profileId, @PathVariable("uuid")String uuid) {
		return profileServiceBean.addJob(profileId, uuid);
	}
	
	@DeleteMapping("/delete/{id}")
	public Profile deleteProfileById(@PathVariable("id")int id) {
		return profileServiceBean.deleteProfileById(id);
	}
	
	@PutMapping("/update")
	public Profile updateProfile(@RequestBody Profile profile) {
		return profileServiceBean.updateProfile(profile);
	}
}