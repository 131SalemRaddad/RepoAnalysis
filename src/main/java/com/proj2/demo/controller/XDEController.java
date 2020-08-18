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

import com.proj2.demo.model.Cli;
import com.proj2.demo.model.XDE;
import com.proj2.demo.service.XDEService;

@RestController
@RequestMapping("/xde")
public class XDEController {
	@Autowired
	private XDEService xdeServiceBean;
	
	@GetMapping("/getAll")
	public List<XDE> getAllXDEs() {
		return xdeServiceBean.getAllXDEs();
	}
	
	@GetMapping("/get/{id}")
	public XDE getXDEById(@PathVariable("id")int id) {
		return xdeServiceBean.getXDEById(id);
	}
	
	@GetMapping("/getByName/{xdeName}")
	public XDE getByName(@PathVariable("xdeName")String xdeName) {
		return xdeServiceBean.getByName(xdeName);
	}
	
	@GetMapping("/getCliList/{xdeName}")
	public List<Cli> getCliList(@PathVariable("xdeName")String xdeName){
		return xdeServiceBean.getCliList(xdeName);
	}
	
	@GetMapping("/getMibList/{xdeName}")
	public List<String> getMibList(@PathVariable("xdeName")String xdeName){
		return xdeServiceBean.getMibList(xdeName);
	}
	
	@PostMapping("/add")
	public String saveXDE(@RequestBody XDE xde) {
		return xdeServiceBean.saveXDE(xde);
	}
	
	@PostMapping("/addAll")
	public String saveAllXDEs(@RequestBody List<XDE> xdeList) {
		return xdeServiceBean.saveAllXDEs(xdeList);
	}
	
	@PostMapping("/addJob/{xdeId}/{uuid}")
	public String saveJob(@PathVariable("xdeId")int xdeId, @PathVariable("uuid")String uuid) {
		return xdeServiceBean.addJob(xdeId, uuid);
	}
	
	@DeleteMapping("/delete/{id}")
	public XDE deleteXDEById(@PathVariable("id")int id) {
		return xdeServiceBean.deleteXDEById(id);
	}
	
	@PutMapping("/update")
	public XDE updateXDE(@RequestBody XDE xde) {
		return xdeServiceBean.updateXDE(xde);
	}
}