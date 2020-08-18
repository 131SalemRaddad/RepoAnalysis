package com.proj2.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proj2.demo.exception.APIException;
import com.proj2.demo.model.Cli;
import com.proj2.demo.model.Job;
import com.proj2.demo.model.XDE;
import com.proj2.demo.repository.JobRepo;
import com.proj2.demo.repository.XDERepo;

@Service
public class XDEServiceImpl implements XDEService{
	@Autowired
	private XDERepo rep;
	
	@Autowired
	private JobRepo jobRepo;
	
	@Override
	public List<XDE> getAllXDEs() {
		try {
			return rep.findAll();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public XDE getXDEById(int id) {
		try {
			Optional<XDE> optXDE = rep.findById(id);
			if(optXDE.isEmpty())
				throw new APIException("XDE not found", HttpStatus.NOT_FOUND);
			
			return optXDE.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String saveXDE(XDE xde) {
		try {
			rep.save(xde);
			return "Added successfully";
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public XDE deleteXDEById(int id) {
		try {
			Optional<XDE> optXDE = rep.findById(id);
			if(optXDE.isEmpty())
				throw new APIException("XDE not found", HttpStatus.NOT_FOUND);
			
			rep.deleteById(id);
			return optXDE.get();
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public XDE updateXDE(XDE xde) {
		try {
			Optional<XDE> optXDE = rep.findById(xde.getId());
			if(optXDE.isEmpty())
				throw new APIException("XDE not found", HttpStatus.NOT_FOUND);
			
			XDE prevXDE = optXDE.get();
			BeanUtils.copyProperties(xde, optXDE.get());
			rep.save(optXDE.get());
			return prevXDE;
		}catch(Exception e) {
			if(e instanceof APIException)
				throw e;
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public List<Cli> getCliList(String xdeName) {
		return rep.findCliList(xdeName);
	}

	@Override
	public List<String> getMibList(String xdeName) {
		return rep.findMibList(xdeName);
	}

	@Override
	public String saveAllXDEs(List<XDE> xdeList) {
		try {
			rep.saveAll(xdeList);
			return "Done successfully";
		}catch(Exception e) {
			throw new APIException(e.getMessage());
		}
	}

	@Override
	public String addJob(int xdeId, String uuid) {
		Optional<XDE> optXDE = rep.findById(xdeId);
		if(optXDE.isEmpty())
			throw new APIException("XDE not found", HttpStatus.NOT_FOUND);
		
		Optional<Job> optJob = jobRepo.findById(uuid);
		if(optJob.isEmpty())
			throw new APIException("Job not found", HttpStatus.NOT_FOUND);
		
		optJob.get().getXdes().add(optXDE.get());
		optXDE.get().setJob(optJob.get());
		
		return "Done successfully";
	}

	@Override
	public XDE getByName(String xdeName) {
		return rep.findByName(xdeName);
	}
}