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

import com.proj2.demo.model.Feature;
import com.proj2.demo.service.FeatureService;

@RestController
@RequestMapping("/feature")
public class FeatureController {
	@Autowired
	private FeatureService featureServiceBean;
	
	@GetMapping("/getAll")
	public List<Feature> getAllFeatures() {
		return featureServiceBean.getAllFeatures();
	}
	
	@GetMapping("/get/{id}")
	public Feature getFeatureById(@PathVariable("id")int id) {
		return featureServiceBean.getFeatureById(id);
	}
	
	@GetMapping("/getByXDEName/{xdeName}")
	public List<Feature> findFeaturesUsingXDE(@PathVariable("xdeName")String xdeName){
		return featureServiceBean.findFeaturesUsingXDE(xdeName);
	}
	
	@GetMapping("/getXDEUsers")
	public List<Feature> findFeaturesUsesXDE(){
		return featureServiceBean.findFeaturesUsesXDE();
	}
	
	@GetMapping("/getByCliText/{cliText}")
	public List<Feature> findFeaturesContainingCliText(@PathVariable("cliText")String cliText){
		return featureServiceBean.findFeaturesContainingCliText(cliText);
	}
	
	@GetMapping("/getByMibText/{mibText}")
	public List<Feature> findFeaturesContainingMibText(@PathVariable("mibText")String mibText){
		return featureServiceBean.findFeaturesContainingMibText(mibText);
	}
	
	@PostMapping("/add")
	public String saveFeature(@RequestBody Feature feature) {
		return featureServiceBean.saveFeature(feature);
	}
	
	@PostMapping("/addAll")
	public String saveAllFeatures(@RequestBody List<Feature> featureList) {
		return featureServiceBean.saveAllFeatures(featureList);
	}
	
	@PostMapping("/addJob/{featureId}/{uuid}")
	public String saveJob(@PathVariable("featureId")int featureId, @PathVariable("uuid")String uuid) {
		return featureServiceBean.addJob(featureId, uuid);
	}
	
	@DeleteMapping("/delete/{id}")
	public Feature deleteById(@PathVariable("id")int id) {
		return featureServiceBean.deleteFeatureById(id);
	}
	
	@PutMapping("/update")
	public Feature updateFeature(@RequestBody Feature feature) {
		return featureServiceBean.updateFeature(feature);
	}
}