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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table
@Getter @Setter
public class Profile {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Info info;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private ProfileParent parent;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
    @CollectionTable(name = "feature_list", joinColumns = @JoinColumn(name = "profile_id"))
	private List<Info> featureList = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	  name = "features", joinColumns = @JoinColumn(name = "profile_id"),
	  inverseJoinColumns = @JoinColumn(name = "feature_id"))
	private List<Feature> features = new ArrayList<>();
	
	@ElementCollection
    @CollectionTable(name = "profile_exclusion", joinColumns = @JoinColumn(name = "profile_id"))
	@AttributeOverrides({
		  @AttributeOverride( name = "info", column = @Column(name = "exclusion_info"))
	})
	private List<Exclusion> exclusionList = new ArrayList<>();
	
	@ElementCollection
    @CollectionTable(name = "profile_override", joinColumns = @JoinColumn(name = "profile_id"))
	@AttributeOverrides({
		  @AttributeOverride( name = "override", column = @Column(name = "override_info"))
	})
	private List<Exclusion> overrideList = new ArrayList<>();
	
	@ElementCollection
    @CollectionTable(name = "profile_configuration", joinColumns = @JoinColumn(name = "profile_id"))
	@AttributeOverrides({
		  @AttributeOverride( name = "property", column = @Column(name = "configuration_property")),
		  @AttributeOverride( name = "params", column = @Column(name = "configuration_params")),
		  @AttributeOverride( name = "value", column = @Column(name = "configuration_value"))
	})
	private List<Configuration> configList = new ArrayList<>();
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Job.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name="job_uuid", referencedColumnName = "uuid")
    private Job job;
}