package com.proj2.demo.model;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Snmp {
	@Type(type = "org.hibernate.type.TextType")
	private String location;
	@Type(type = "org.hibernate.type.TextType")
	private String mib;
}