package com.proj2.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table
@Getter @Setter
public class Job {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid")
    private UUID uuid;
	
	private String branch;
	private String owner;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	@JsonIgnore
	@OneToMany(mappedBy="job", fetch = FetchType.LAZY, targetEntity = Profile.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="job", fetch = FetchType.LAZY, targetEntity = Feature.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Feature> features = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="job", fetch = FetchType.LAZY, targetEntity = XDE.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<XDE> xdes = new ArrayList<>();
}