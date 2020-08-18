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
import com.proj2.demo.model.XDE;
import com.proj2.demo.repository.FeatureRepo;
import com.proj2.demo.repository.JobRepo;
import com.proj2.demo.repository.XDERepo;

@Service
public class FeatureServiceImpl implements FeatureService {
	@Autowired
	private FeatureRepo rep;
	
	@Autowired
	private XDERepo xdeRepo;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Override
	public List<Feature> getAllFeatures() {
		try {
			return rep.findAll();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Feature getFeatureById(int id) {
		try {
			Optional<Feature> feature = rep.findById(id);
			if(feature.isEmpty())
				throw new APIException("Feature not found", HttpStatus.NOT_FOUND);
			
			return feature.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String saveFeature(Feature feature) {
		try {
			List<Info> xdeList = feature.getXdeList();
			for(int i=0;i<xdeList.size();i++) {
				XDE xde = xdeRepo.findByName(xdeList.get(i).getArtifactId());
				if(xde != null) {
					xde.getFeatureList().add(feature);
					feature.getXdes().add(xde);
				}
			}
			rep.save(feature);
			return "Added successfully";
		}catch(Exception e) {
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Feature deleteFeatureById(int id) {
		try {
			Optional<Feature> optFeature = rep.findById(id);
			if(optFeature.isEmpty())
				throw new APIException("Feature not found", HttpStatus.NOT_FOUND);
			
			rep.deleteById(id);
			return optFeature.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public Feature updateFeature(Feature feature) {
		try {
			Optional<Feature> optFeature = rep.findById(feature.getId());
			if(optFeature.isEmpty())
				throw new APIException("Feature not found", HttpStatus.NOT_FOUND);
			
			Feature prevFeature = optFeature.get();
			BeanUtils.copyProperties(feature, optFeature.get());
			rep.save(optFeature.get());
			return prevFeature;
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public List<Feature> findFeaturesUsingXDE(String xdeName) {
		return rep.findFeaturesUsingXDE(xdeName);
	}

	@Override
	public List<Feature> findFeaturesUsesXDE() {
		return rep.findFeaturesUsesXDE();
	}

	@Override
	public String saveAllFeatures(List<Feature> featureList) {
		try {
			List<Feature> checkedFeatureList = new ArrayList<>();
			for(int i=0;i<featureList.size();i++) {
				List<Info> xdeList = featureList.get(i).getXdeList();
				for(int j=0;j<xdeList.size();j++) {
					XDE xde = xdeRepo.findByName(xdeList.get(j).getArtifactId());
					if(xde != null) {
						xde.getFeatureList().add(featureList.get(i));
						featureList.get(i).getXdes().add(xde);
						checkedFeatureList.add(featureList.get(i));
					}
				}
			}
			rep.saveAll(checkedFeatureList);
			return "Done successfully";
		}catch(Exception e) {
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String addJob(int featureId, String uuid) {
		Optional<Feature> optFeature = rep.findById(featureId);
		if(optFeature.isEmpty())
			throw new APIException("Feature not found", HttpStatus.NOT_FOUND);
		
		Optional<Job> optJob = jobRepo.findById(uuid);
		if(optJob.isEmpty())
			throw new APIException("Job not found", HttpStatus.NOT_FOUND);
		
		optJob.get().getFeatures().add(optFeature.get());
		optFeature.get().setJob(optJob.get());
		
		return "Done successfully";
	}

	@Override
	public List<Feature> findFeaturesContainingCliText(String cliText) {
		return rep.findFeaturesContainingCliText(cliText);
	}

	@Override
	public List<Feature> findFeaturesContainingMibText(String mibText) {
		return rep.findFeaturesContainingMibText(mibText);
	}
}