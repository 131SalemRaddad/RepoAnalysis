package com.proj2.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table
@Getter @Setter
public class ProfileParent {
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Info info;
	
	@JsonIgnore
	@OneToOne(mappedBy = "parent")
	private Profile profile;
}