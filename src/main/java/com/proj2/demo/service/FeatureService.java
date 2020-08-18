package com.proj2.demo.service;

import java.util.List;

import com.proj2.demo.model.Feature;

public interface FeatureService {
	// get all features
	public List<Feature> getAllFeatures();
	
	// get a feature by id
	public Feature getFeatureById(int id);
	
	// add a new feature
	public String saveFeature(Feature feature);
	
	// add a new list of features
	public String saveAllFeatures(List<Feature> featureList);
	
	// delete an existed feature by id
	public Feature deleteFeatureById(int id);
	
	// update an existed feature
	public Feature updateFeature(Feature feature);
	
	// searching for features that uses XDE by name
	public List<Feature> findFeaturesUsingXDE(String xdeName);
	
	// searching for features that uses XDE
	public List<Feature> findFeaturesUsesXDE();
	
	// searching for features by part of cli command
	public List<Feature> findFeaturesContainingCliText(String cliText);
	
	// searching for features by part of mib command
	public List<Feature> findFeaturesContainingMibText(String mibText);
	
	// add a job
	public String addJob(int featureId, String uuid);
}