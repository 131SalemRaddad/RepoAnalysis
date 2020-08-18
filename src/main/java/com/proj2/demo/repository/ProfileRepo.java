package com.proj2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proj2.demo.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer>{
	@Query("select p from Profile p join p.features f join f.xdes x where x != null")
	public List<Profile> findProfilesUsesXDE();
}