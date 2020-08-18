package com.proj2.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private String groupId;
	private String artifactId;
	private String version;
	private String type;
}