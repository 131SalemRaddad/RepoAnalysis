package com.proj2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proj2.demo.model.Feature;

@Repository
@Transactional
public interface FeatureRepo extends JpaRepository<Feature, Integer>{
	@Query("select f from Feature f join f.xdes x where x.info.artifactId = ?1")
	public List<Feature> findFeaturesUsingXDE(String xdeName);
	
	@Query("select f from Feature f join f.xdes x where x != null")
	public List<Feature> findFeaturesUsesXDE();
	
	@Query("select f from Feature f where f.info.artifactId = ?1")
	public Feature findByName(String featureName);
	
	@Query("select f from Feature f join f.xdes x join x.cliList c where c.command like %?1%")
	public List<Feature> findFeaturesContainingCliText(String cliText);
	
	@Query("select f from Feature f join f.xdes x join x.snmpList s where s.mib like %?1%")
	public List<Feature> findFeaturesContainingMibText(String mibText);
}