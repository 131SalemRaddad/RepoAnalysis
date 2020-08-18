package com.proj2.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Feature {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Info info;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
    @CollectionTable(name = "xde_list", joinColumns = @JoinColumn(name = "feature_id"))
	private List<Info> xdeList = new ArrayList<>();
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	  name = "xdes", joinColumns = @JoinColumn(name = "feature_id"),
	  inverseJoinColumns = @JoinColumn(name = "xde_id"))
	private List<XDE> xdes = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "features", cascade = CascadeType.ALL)
	private List<Profile> profileList = new ArrayList<>();
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Job.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name="job_uuid", referencedColumnName = "uuid")
    private Job job;
}