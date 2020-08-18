package com.proj2.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table
@Getter @Setter
public class XDE {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Info info;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
    @CollectionTable(name = "xde_cli", joinColumns = @JoinColumn(name = "xde_id"))
	@AttributeOverrides({
		  @AttributeOverride( name = "location", column = @Column(name = "cli_location")),
		  @AttributeOverride( name = "command", column = @Column(name = "cli_command"))
	})
	private List<Cli> cliList = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
    @CollectionTable(name = "xde_snmp", joinColumns = @JoinColumn(name = "xde_id"))
	@AttributeOverrides({
		  @AttributeOverride( name = "location", column = @Column(name = "snmp_location")),
		  @AttributeOverride( name = "mib", column = @Column(name = "snmp_mib"))
	})
	private List<Snmp> snmpList = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "xdes", cascade = CascadeType.ALL)
	private List<Feature> featureList = new ArrayList<>();
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Job.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name="job_uuid", referencedColumnName = "uuid")
    private Job job;
}