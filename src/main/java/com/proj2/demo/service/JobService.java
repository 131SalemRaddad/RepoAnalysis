package com.proj2.demo.service;

import java.util.List;

import com.proj2.demo.model.Job;

public interface JobService {
	// get all jobs
	public List<Job> getAllJobs();
	
	// get a job by uuid
	public Job getJob(String uuid);
	
	// add a job
	public String saveJob(Job job);
}