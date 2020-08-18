package com.proj2.demo.service;

import java.util.List;

import com.proj2.demo.model.Cli;
import com.proj2.demo.model.XDE;

public interface XDEService {
	// get all XDEs
	public List<XDE> getAllXDEs();
	
	// get a XDE by id
	public XDE getXDEById(int id);
	
	// add a new XDE
	public String saveXDE(XDE xde);
	
	// add a new list of XDEs
	public String saveAllXDEs(List<XDE> xdeList);
	
	// delete a XDE by id
	public XDE deleteXDEById(int id);
	
	// update an existed XDE
	public XDE updateXDE(XDE xde);
	
	// get Cli list in XDE
	public List<Cli> getCliList(String xdeName);
	
	// get mib list in XDE
	public List<String> getMibList(String xdeName);
	
	// add a job
	public String addJob(int xdeId, String uuid);
	
	// get xde by name
	public XDE getByName(String xdeName);
}