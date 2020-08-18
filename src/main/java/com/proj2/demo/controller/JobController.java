package com.proj2.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj2.demo.model.Job;
import com.proj2.demo.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobServiceBean;
	
	@GetMapping("/getAll")
	public List<Job> getAllJobs(){
		return jobServiceBean.getAllJobs();
	}
	
	@GetMapping("/get/{uuid}")
	public Job getJob(@PathVariable("uuid")String uuid) {
		return jobServiceBean.getJob(uuid);
	}
	
	@PostMapping("/add")
	public String saveJob(@RequestBody Job job) {
		return jobServiceBean.saveJob(job);
	}
}