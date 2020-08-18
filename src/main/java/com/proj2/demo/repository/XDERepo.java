package com.proj2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proj2.demo.model.Cli;
import com.proj2.demo.model.XDE;

@Repository
@Transactional
public interface XDERepo extends JpaRepository<XDE, Integer>{
	@Query("select c from XDE x join x.cliList c where x.info.artifactId = ?1")
	public List<Cli> findCliList(String xdeName);
	
	@Query("select s.mib from XDE x join x.snmpList s where x.info.artifactId = ?1")
	public List<String> findMibList(String xdeName);
	
	@Query("select x from XDE x where x.info.artifactId = ?1")
	public XDE findByName(String xdeName);
}