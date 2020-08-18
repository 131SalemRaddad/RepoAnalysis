package com.proj2.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proj2.demo.exception.APIException;
import com.proj2.demo.model.Job;
import com.proj2.demo.repository.JobRepo;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepo rep;
	
	@Override
	public List<Job> getAllJobs() {
		return rep.findAll();
	}

	@Override
	public Job getJob(String uuid) {
		Optional<Job> job = rep.findById(uuid);
		if(job.isEmpty())
			throw new APIException("The job not found", HttpStatus.NOT_FOUND);
		
		return job.get();
	}

	@Override
	public String saveJob(Job job) {
		rep.save(job);
		return job.getUuid()+"";
	}

}