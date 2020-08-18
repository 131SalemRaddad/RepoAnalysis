package com.proj2.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proj2.demo.exception.APIException;
import com.proj2.demo.model.Feature;
import com.proj2.demo.model.Info;
import com.proj2.demo.model.Job;
import com.proj2.demo.model.Profile;
import com.proj2.demo.repository.FeatureRepo;
import com.proj2.demo.repository.JobRepo;
import com.proj2.demo.repository.ProfileRepo;

@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	private ProfileRepo rep;
	
	@Autowired
	private FeatureRepo featureRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Override
	public List<Profile> getAllProfiles() {
		try {
			return rep.findAll();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Profile getProfileById(int id) {
		try {
			Optional<Profile> profile = rep.findById(id);
			if(profile.isEmpty())
				throw new APIException("Profile not found", HttpStatus.NOT_FOUND);
			
			return profile.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String saveProfile(Profile profile) {
		try {
			List<Info> featureList = profile.getFeatureList();
			for(int i=0;i<featureList.size();i++) {
				Feature feature = featureRepo.findByName(featureList.get(i).getArtifactId());
				if(feature != null) {
					feature.getProfileList().add(profile);
					profile.getFeatures().add(feature);
				}
			}
			rep.save(profile);
			return "Added successfully";
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Profile deleteProfileById(int id) {
		try {
			Optional<Profile> optProfile = rep.findById(id);
			if(optProfile.isEmpty())
				throw new APIException("Profile not found", HttpStatus.NOT_FOUND);
			
			rep.deleteById(id);
			return optProfile.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Profile updateProfile(Profile profile) {
		try {
			Optional<Profile> optProfile = rep.findById(profile.getId());
			if(optProfile.isEmpty())
				throw new APIException("Feature not found", HttpStatus.NOT_FOUND);
			
			Profile prevProfile = optProfile.get();
			BeanUtils.copyProperties(profile, optProfile.get());
			rep.save(optProfile.get());
			return prevProfile;
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public List<Profile> findProfilesUsesXDE() {
		return rep.findProfilesUsesXDE();
	}

	@Override
	public String saveAllProfiles(List<Profile> profileList) {
		try {
			List<Profile> checkedProfileList = new ArrayList<>();
			for(int i=0;i<profileList.size();i++) {
				List<Info> featureList = profileList.get(i).getFeatureList();
				for(int j=0;j<featureList.size();j++) {
					Feature feature = featureRepo.findByName(featureList.get(j).getArtifactId());
					if(feature != null) {
						feature.getProfileList().add(profileList.get(i));
						profileList.get(i).getFeatures().add(feature);
						checkedProfileList.add(profileList.get(i));
					}
				}
			}
			rep.saveAll(checkedProfileList);
			return "Done successfully";
		}catch(Exception e) {
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String addJob(int profileId, String uuid) {
		Optional<Profile> optProfile = rep.findById(profileId);
		if(optProfile.isEmpty())
			throw new APIException("Profile not found", HttpStatus.NOT_FOUND);
		
		Optional<Job> optJob = jobRepo.findById(uuid);
		if(optJob.isEmpty())
			throw new APIException("Job not found", HttpStatus.NOT_FOUND);
		
		optJob.get().getProfiles().add(optProfile.get());
		optProfile.get().setJob(optJob.get());
		
		return "Done successfully";
	}
}